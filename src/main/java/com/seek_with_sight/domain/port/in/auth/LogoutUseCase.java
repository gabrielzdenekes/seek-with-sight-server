package com.seek_with_sight.domain.port.in.auth;

public interface LogoutUseCase {
    void logout(String refreshToken);
}
