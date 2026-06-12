package com.seek_with_sight.email.application.port.out;

import jakarta.mail.MessagingException;

public interface EmailSenderPort {
    void sendVerificationEmail(
            String to,
            String verificationUrl
    ) throws MessagingException;
}
