package com.seek_with_sight.application.port.in.email;

public interface VerifyEmailUseCase {
    void verify(String rawToken);
}
