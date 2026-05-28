package com.seek_with_sight.application.service.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.email.ResendVerificationUseCase;
import com.seek_with_sight.domain.port.in.email.SendVerificationEmailUseCase;
import com.seek_with_sight.domain.port.in.email.VerifyEmailUseCase;
import com.seek_with_sight.domain.port.out.email.EmailSenderPort;
import com.seek_with_sight.domain.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.config.application.ApplicationProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class EmailService implements VerifyEmailUseCase, ResendVerificationUseCase, SendVerificationEmailUseCase {
    private final VerificationTokenRepositoryPort tokenRepository;
    private final UserRepositoryPort userRepository;
    private final EmailSenderPort emailSender;
    private final ApplicationProperties appProperties;

    @Override
    public void verify(String rawToken) {

    }

    @Override
    public void resend(String email) {

    }

    @Override
    public void sendVerificationEmail(User user) {
        var token = createToken(user);
        var url = appProperties.baseUrl() + "/api/v1/email/verify?token=" + token.getToken();

        try {
            emailSender.sendVerificationEmail(user.getEmail(), url);
        } catch (Exception e) {
            log.warn("Failed to send verification email", e);
        }
    }

    private EmailVerificationToken createToken(User user) {
        var token = new EmailVerificationToken();

        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS));
        token.setUserId(user.getId());

        return tokenRepository.save(token);
    }
}
