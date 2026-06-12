package com.seek_with_sight.email.infrastructure.out.config.bean;

import com.seek_with_sight.email.application.service.EmailService;
import com.seek_with_sight.email.application.port.in.ResendVerificationUseCase;
import com.seek_with_sight.email.application.port.in.SendVerificationEmailUseCase;
import com.seek_with_sight.email.application.port.in.VerifyEmailUseCase;
import com.seek_with_sight.email.application.port.out.EmailSenderPort;
import com.seek_with_sight.email.application.port.out.VerificationTokenRepositoryPort;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.email.infrastructure.out.EmailSenderAdapter;
import com.seek_with_sight.email.infrastructure.out.persistence.VerificationTokenRepositoryAdapter;
import com.seek_with_sight.email.infrastructure.out.persistence.mapper.VerificationTokenPersistenceMapper;
import com.seek_with_sight.email.infrastructure.out.persistence.repository.VerificationTokenJpaRepository;
import com.seek_with_sight.infrastructure.config.application.ApplicationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;

@Configuration
public class EmailBeanConfig {
    @Bean
    public VerifyEmailUseCase verifyEmailUseCase(
            VerificationTokenRepositoryPort verificationTokenRepositoryPort,
            UserRepositoryPort userRepository,
            EmailSenderPort emailSenderPort,
            ApplicationProperties appProps
    ) {
        return new EmailService(verificationTokenRepositoryPort, userRepository, emailSenderPort, appProps);
    }

    @Bean
    public ResendVerificationUseCase resendVerificationUseCase(
            VerificationTokenRepositoryPort verificationTokenRepositoryPort,
            UserRepositoryPort userRepository,
            EmailSenderPort emailSenderPort,
            ApplicationProperties appProps
    ) {
        return new EmailService(verificationTokenRepositoryPort, userRepository, emailSenderPort, appProps);
    }

    @Bean
    public SendVerificationEmailUseCase sendVerificationEmailUseCase(
            VerificationTokenRepositoryPort verificationTokenRepositoryPort,
            UserRepositoryPort userRepository,
            EmailSenderPort emailSenderPort,
            ApplicationProperties appProps
    ) {
        return new EmailService(verificationTokenRepositoryPort, userRepository, emailSenderPort, appProps);
    }

    @Bean
    public VerificationTokenRepositoryPort verificationTokenRepository(
            VerificationTokenJpaRepository repository,
            VerificationTokenPersistenceMapper mapper
    ) {
        return new VerificationTokenRepositoryAdapter(repository, mapper);
    }

    @Bean
    public EmailSenderPort emailSenderPort(
            JavaMailSender sender,
            TemplateEngine templateEngine
    ) {
        return new EmailSenderAdapter(sender, templateEngine);
    }
}
