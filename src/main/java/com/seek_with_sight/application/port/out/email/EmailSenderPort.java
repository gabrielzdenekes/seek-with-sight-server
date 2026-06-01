package com.seek_with_sight.application.port.out.email;

import jakarta.mail.MessagingException;

public interface EmailSenderPort {
    void sendVerificationEmail(
            String to,
            String verificationUrl
    ) throws MessagingException;
}
