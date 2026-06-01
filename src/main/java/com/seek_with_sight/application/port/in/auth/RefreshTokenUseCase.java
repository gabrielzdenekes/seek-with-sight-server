package com.seek_with_sight.application.port.in.auth;

import com.seek_with_sight.domain.model.auth.JwtLoginData;

public interface RefreshTokenUseCase {
    JwtLoginData refreshToken(String refreshToken);
}
