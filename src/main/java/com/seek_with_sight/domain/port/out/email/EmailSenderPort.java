package com.seek_with_sight.domain.port.out.email;

import jakarta.mail.MessagingException;

public interface EmailSenderPort {
    void sendVerificationEmail(
            String to,
            String verificationUrl
    ) throws MessagingException;
}
