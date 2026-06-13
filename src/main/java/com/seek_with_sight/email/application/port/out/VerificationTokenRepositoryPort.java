package com.seek_with_sight.email.application.port.out;

import com.seek_with_sight.email.domain.model.EmailVerificationToken;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenRepositoryPort {
    EmailVerificationToken save(EmailVerificationToken token);

    Optional<EmailVerificationToken> findByToken(String tokenHash);

    void invalidateUserTokens(UUID userId);
}
