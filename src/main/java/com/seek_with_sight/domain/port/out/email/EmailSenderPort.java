package com.seek_with_sight.domain.port.out.email;

public interface EmailSenderPort {
    void sendVerificationEmail(
            String to,
            String verificationUrl
    );
}
