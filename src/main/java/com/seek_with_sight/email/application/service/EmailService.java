package com.seek_with_sight.email.application.service;

import com.seek_with_sight.email.domain.exception.EmailTokenAlreadyUsedException;
import com.seek_with_sight.email.domain.exception.EmailTokenExpiredException;
import com.seek_with_sight.email.domain.exception.EmailTokenNotFoundException;
import com.seek_with_sight.email.domain.model.EmailVerificationToken;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.email.application.port.in.ResendVerificationUseCase;
import com.seek_with_sight.email.application.port.in.SendVerificationEmailUseCase;
import com.seek_with_sight.email.application.port.in.VerifyEmailUseCase;
import com.seek_with_sight.email.application.port.out.EmailSenderPort;
import com.seek_with_sight.email.application.port.out.VerificationTokenRepositoryPort;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.shared.infrastructure.config.application.ApplicationProperties;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements VerifyEmailUseCase, ResendVerificationUseCase, SendVerificationEmailUseCase {
    private final VerificationTokenRepositoryPort tokenRepository;
    private final UserRepositoryPort userRepository;
    private final EmailSenderPort emailSender;
    private final ApplicationProperties appProperties;

    @Override
    @Transactional
    public void verify(String rawToken) {
        var token = tokenRepository.findByToken(rawToken)
                .orElseThrow(EmailTokenNotFoundException::new);

        if (token.isUsed()) {
            throw new EmailTokenAlreadyUsedException();
        }

        var isExpired = token.getExpiresAt().isBefore(Instant.now());
        if (isExpired) {
            throw new EmailTokenExpiredException();
        }

        var user = userRepository.findById(token.getUserId()).orElseThrow();
        user.setEmailVerified(true);
        userRepository.save(user);

        token.setUsed(true);
        tokenRepository.save(token);
    }

    @Override
    public void resend(String email) {
        var user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow();

        if (user.getEmailVerified()) {
            return;
        }

        tokenRepository.invalidateUserTokens(user.getId());

        sendVerificationEmail(user);
    }

    @Override
    public void sendVerificationEmail(User user) {
        var token = createToken(user);
        var url = appProperties.baseUrl() + "/api/email/verify?token=" + token.getToken();

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
