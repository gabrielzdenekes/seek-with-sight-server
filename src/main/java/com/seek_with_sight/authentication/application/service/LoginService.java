package com.seek_with_sight.authentication.application.service;

import com.seek_with_sight.authentication.domain.exception.UnauthorizedException;
import com.seek_with_sight.user.domain.exception.EmailNotVerifiedException;
import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.authentication.application.port.in.LoginCommand;
import com.seek_with_sight.authentication.application.port.in.LoginUseCase;
import com.seek_with_sight.authentication.application.port.out.JwtTokenPort;
import com.seek_with_sight.authentication.application.port.out.RefreshTokenPort;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoginService implements LoginUseCase {
    private final UserRepositoryPort userRepository;
    private final RefreshTokenPort refreshTokenPort;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenPort jwtTokenPort;

    @Override
    public JwtLoginData login(LoginCommand loginCommand) {
        log.info("Login attempt for email={}", loginCommand.email());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginCommand.email(),
                        loginCommand.password()
                )
        );

        var user = userRepository.findByEmailIgnoreCase(loginCommand.email())
                .orElseThrow(() -> {
                    log.warn("User not found for email={}", loginCommand.email());
                    return new UnauthorizedException(loginCommand.email());
                });

        if (!user.getEmailVerified()) {
            throw new EmailNotVerifiedException();
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
