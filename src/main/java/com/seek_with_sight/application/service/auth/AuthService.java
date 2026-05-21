package com.seek_with_sight.application.service.auth;

import com.seek_with_sight.domain.exception.security.UnauthorizedException;
import com.seek_with_sight.domain.model.auth.JwtLoginData;
import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.domain.port.in.auth.LoginCommand;
import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.security.RefreshTokenPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService implements LoginUseCase, RefreshTokenUseCase {
    private final JwtTokenPort jwtTokenPort;
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public JwtLoginData login(LoginCommand loginCommand) {
        var user = userRepository.findByEmailIgnoreCase(loginCommand.email())
                .orElseThrow(() -> new UnauthorizedException(loginCommand.email()));

        if (!passwordEncoder.matches(loginCommand.password(), user.getPassHash())) {
            throw new UnauthorizedException("Invalid login credentials");
        }

        var accessToken = jwtTokenPort.generateAccessToken(user);
        var refreshToken = jwtTokenPort.generateRefreshToken(user);
        var loginData = new JwtLoginData(
                accessToken,
                refreshToken,
                user
        );

        handleRefreshTokenPersistence(loginData);

        return loginData;
    }

    @Override
    public JwtLoginData refreshToken(String refreshToken) {
        if (!StringUtils.hasLength(refreshToken)) {
            throw new UnauthorizedException("Empty token.");
        }

        var token = refreshTokenPort.findByToken(refreshToken)
                .orElseThrow(() -> new UnauthorizedException("Refresh token not found"));

        if (!jwtTokenPort.isValidRefreshToken(token)) {
            throw new UnauthorizedException("Refresh token has expired");
        }

        var user = token.getUser();
        var newAccessToken = jwtTokenPort.generateAccessToken(user);

        return new JwtLoginData(
                newAccessToken,
                refreshToken,
                user
        );
    }

    private void handleRefreshTokenPersistence(JwtLoginData jwtLoginData) {
        var user = jwtLoginData.getUser();
        var refreshToken = refreshTokenPort.findByUserId(user.getId())
                .orElseGet(() -> {
                    var newRefreshToken = new RefreshToken();

                    newRefreshToken.setUser(user);

                    return newRefreshToken;
                });

        var refreshTokenString = jwtLoginData.getRefreshToken();
        var expiresAt = jwtTokenPort.extractExpiration(refreshTokenString);

        refreshToken.setToken(refreshTokenString);
        refreshToken.setExpiresAt(expiresAt);

        refreshTokenPort.save(refreshToken);
    }
}
