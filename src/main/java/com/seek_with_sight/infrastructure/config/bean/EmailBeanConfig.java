package com.seek_with_sight.infrastructure.config.bean;

import com.seek_with_sight.application.service.email.VerifyEmailService;
import com.seek_with_sight.domain.port.out.email.EmailSenderPort;
import com.seek_with_sight.domain.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import com.seek_with_sight.infrastructure.adapter.out.email.EmailSenderAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.VerificationTokenRepositoryAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.mapper.VerificationTokenPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.repository.VerificationTokenJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class EmailBeanConfig {
    @Bean
    public VerifyEmailService verifyEmailService() {
        return new VerifyEmailService();
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
            LocalizedMessageService messageService
    ) {
        return new EmailSenderAdapter(sender, messageService);
    }
}
