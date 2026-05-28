package com.seek_with_sight.application.service.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.email.ResendVerificationUseCase;
import com.seek_with_sight.domain.port.in.email.SendVerificationEmailUseCase;
import com.seek_with_sight.domain.port.in.email.VerifyEmailUseCase;
import com.seek_with_sight.domain.port.out.email.EmailSenderPort;
import com.seek_with_sight.domain.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.domain.port.out.user.UserRepositoryPort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService implements VerifyEmailUseCase, ResendVerificationUseCase, SendVerificationEmailUseCase {
    private final VerificationTokenRepositoryPort tokenRepository;
    private final UserRepositoryPort userRepository;
    private final EmailSenderPort emailSender;

    @Override
    public void verify(String rawToken) {

    }

    @Override
    public void resend(String email) {

    }

    @Override
    public void sendVerificationEmail(User user) {
        var token = createToken(user);
        // TODO: refactor
        var url = "https://localhost:8443/api/v1/email/verify?token=" + token.getToken();

        emailSender.sendVerificationEmail(user.getEmail(), url);
    }

    private EmailVerificationToken createToken(User user) {
        var token = new EmailVerificationToken();

        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Instant.now().plus(24, ChronoUnit.HOURS));
        token.setUserId(user.getId());

        return tokenRepository.save(token);
    }
}
