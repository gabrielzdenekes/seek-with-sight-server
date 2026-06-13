package com.seek_with_sight.authentication.application.port.in;

public interface LogoutUseCase {
    void logout(String refreshToken);
}
