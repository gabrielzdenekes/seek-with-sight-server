package com.seek_with_sight.domain.port.out.security;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.domain.model.user.User;

public interface JwtTokenPort {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    boolean isValidRefreshToken(RefreshToken refreshToken);
}
