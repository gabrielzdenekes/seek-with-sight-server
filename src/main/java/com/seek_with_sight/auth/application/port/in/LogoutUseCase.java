package com.seek_with_sight.auth.application.port.in;

public interface LogoutUseCase {
    void logout(String refreshToken);
}
