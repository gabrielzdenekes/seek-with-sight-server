package com.seek_with_sight.authentication.application.port.out;

import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.user.domain.model.User;

import java.time.LocalDateTime;

public interface JwtTokenPort {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean isExpiredRefreshToken(RefreshToken refreshToken);

    LocalDateTime extractExpiration(String token);

    String extractUsername(String token);

    boolean isJwtExpired(String token);
}
