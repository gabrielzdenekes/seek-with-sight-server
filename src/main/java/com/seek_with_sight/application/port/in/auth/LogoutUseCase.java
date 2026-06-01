package com.seek_with_sight.application.port.in.auth;

public interface LogoutUseCase {
    void logout(String refreshToken);
}
