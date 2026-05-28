package com.seek_with_sight.domain.port.in.email;

public interface VerifyEmailUseCase {
    void verify(String rawToken);
}
