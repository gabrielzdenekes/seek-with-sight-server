package com.seek_with_sight.email.application.port.out;

import com.seek_with_sight.email.domain.model.EmailVerificationToken;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface VerificationTokenRepositoryPort extends BaseRepositoryPort<EmailVerificationToken> {
    Optional<EmailVerificationToken> findByToken(String tokenHash);

    void invalidateUserTokens(UUID userId);
}
