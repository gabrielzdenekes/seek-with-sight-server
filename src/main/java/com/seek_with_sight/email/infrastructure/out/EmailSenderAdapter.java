package com.seek_with_sight.email.infrastructure.out;

import com.seek_with_sight.email.application.port.out.EmailSenderPort;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
public class EmailSenderAdapter implements EmailSenderPort {
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Override
    @Async
    public void sendVerificationEmail(String to, String verificationUrl) throws MessagingException {
        var context = new Context();
        context.setVariable("verificationUrl", verificationUrl);
        context.setVariable("name", to);

        var html = templateEngine.process(
                "verification-email",
                context
        );

        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("Verify your email");
        helper.setText(html, true);

        mailSender.send(message);
    }
}
