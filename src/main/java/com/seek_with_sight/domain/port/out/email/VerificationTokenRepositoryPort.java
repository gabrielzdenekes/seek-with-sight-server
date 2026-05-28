package com.seek_with_sight.domain.port.out.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenRepositoryPort {
    EmailVerificationToken save(EmailVerificationToken token);

    Optional<EmailVerificationToken> findByToken(String tokenHash);

    void invalidateUserTokens(UUID userId);
}
