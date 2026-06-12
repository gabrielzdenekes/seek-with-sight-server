package com.seek_with_sight.email.application.port.in;

public interface VerifyEmailUseCase {
    void verify(String rawToken);
}
