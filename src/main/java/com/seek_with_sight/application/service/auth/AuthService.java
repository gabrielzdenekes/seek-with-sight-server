package com.seek_with_sight.application.service.auth;

import com.seek_with_sight.domain.exception.security.UnauthorizedException;
import com.seek_with_sight.domain.model.auth.JwtLoginData;
import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.domain.port.in.auth.LoginCommand;
import com.seek_with_sight.domain.port.in.auth.LoginUseCase;
import com.seek_with_sight.domain.port.in.auth.LogoutUseCase;
import com.seek_with_sight.domain.port.in.auth.RefreshTokenUseCase;
import com.seek_with_sight.domain.port.out.security.JwtTokenPort;
import com.seek_with_sight.domain.port.out.security.PasswordEncoderPort;
import com.seek_with_sight.domain.port.out.security.RefreshTokenPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService implements LoginUseCase, RefreshTokenUseCase, LogoutUseCase {
    private final JwtTokenPort jwtTokenPort;
    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;
    private final RefreshTokenPort refreshTokenPort;

    @Override
    public JwtLoginData login(LoginCommand loginCommand) {
        log.info("Login attempt for email={}", loginCommand.email());

        var user = userRepository.findByEmailIgnoreCase(loginCommand.email())
                .orElseThrow(() -> {
                    log.warn("User not found for email={}", loginCommand.email());
                    return new UnauthorizedException(loginCommand.email());
                });

        if (!passwordEncoder.matches(loginCommand.password(), user.getPassHash())) {
            log.warn("Passwords do not match: userId={}", user.getId());
            throw new UnauthorizedException("Invalid login credentials");
        }

        log.info("Token generation started for userId: {}", user.getId());

        var accessToken = jwtTokenPort.generateAccessToken(user);
        var refreshToken = jwtTokenPort.generateRefreshToken(user);
        var loginData = new JwtLoginData(
                accessToken,
                refreshToken,
                user
        );

        handleRefreshTokenPersistence(loginData);

        log.info("Login successful: userId={}",  user.getId());
        return loginData;
    }

    @Override
    public JwtLoginData refreshToken(String refreshToken) {
        log.info("Refresh attempt started");

        if (!StringUtils.hasLength(refreshToken)) {
            log.warn("Refresh token attempt failed. Refresh token is null or empty.");
            throw new UnauthorizedException("Empty token.");
        }

        var token = refreshTokenPort.findByToken(refreshToken)
                .orElseThrow(() -> {
                    log.warn("Persisted refresh token not found");
                    return new UnauthorizedException("Refresh token not found");
                });

        if (!jwtTokenPort.isExpiredRefreshToken(token)) {
            log.warn("Refresh token has expired.");
            throw new UnauthorizedException("Refresh token has expired");
        }

        var user = token.getUser();
        var newAccessToken = jwtTokenPort.generateAccessToken(user);

        log.info("Refresh token successful. New access token generated for userId={}", user.getId());

        return new JwtLoginData(
                newAccessToken,
                refreshToken,
                user
        );
    }

    @Override
    public void logout(String refreshToken) {
        if (!StringUtils.hasLength(refreshToken)) {
            return;
        }

        refreshTokenPort.deleteByToken(refreshToken);
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

        log.info("Refresh token persisted for userId={}", user.getId());
        refreshTokenPort.save(refreshToken);
    }
}
