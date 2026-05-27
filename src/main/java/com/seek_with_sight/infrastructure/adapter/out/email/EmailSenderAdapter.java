package com.seek_with_sight.infrastructure.adapter.out.email;

import com.seek_with_sight.domain.port.out.email.EmailSenderPort;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class EmailSenderAdapter implements EmailSenderPort {
    private final JavaMailSender mailSender;
    private final LocalizedMessageService messageService;

    @Override
    @Async
    public void sendVerificationEmail(String to, String verificationUrl) {
        var message = new SimpleMailMessage();
        var loc = LocaleContextHolder.getLocale();

        message.setTo(to);
        message.setSubject(messageService.getMessage(
                "email.verification.subject",
                loc
        ));

        message.setText(messageService.getMessage(
                "email.verification.body",
                loc
        ).formatted(verificationUrl));

        mailSender.send(message);
    }
}
