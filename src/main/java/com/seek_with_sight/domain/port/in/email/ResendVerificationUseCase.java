package com.seek_with_sight.domain.port.in.email;

public interface ResendVerificationUseCase {
    void resend(String email);
}
