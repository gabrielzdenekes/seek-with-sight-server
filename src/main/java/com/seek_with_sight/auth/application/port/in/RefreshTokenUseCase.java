package com.seek_with_sight.auth.application.port.in;

import com.seek_with_sight.auth.domain.model.JwtLoginData;

public interface RefreshTokenUseCase {
    JwtLoginData refreshToken(String refreshToken);
}
