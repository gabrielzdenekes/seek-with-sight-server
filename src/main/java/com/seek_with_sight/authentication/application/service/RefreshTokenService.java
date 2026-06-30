package com.seek_with_sight.authentication.application.service;

import com.seek_with_sight.authentication.domain.exception.UnauthorizedException;
import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import com.seek_with_sight.authentication.application.port.in.RefreshTokenUseCase;
import com.seek_with_sight.authentication.application.port.out.JwtTokenPort;
import com.seek_with_sight.authentication.application.port.out.RefreshTokenPort;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService implements RefreshTokenUseCase {
    private final RefreshTokenPort refreshTokenPort;
    private final JwtTokenPort jwtTokenPort;

    @Override
    @Transactional
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
}
