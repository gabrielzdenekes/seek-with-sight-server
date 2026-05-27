package com.seek_with_sight.infrastructure.adapter.out.persistence.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.domain.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.mapper.VerificationTokenPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.repository.VerificationTokenJpaRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class VerificationTokenRepositoryAdapter implements VerificationTokenRepositoryPort {
    private final VerificationTokenJpaRepository repo;
    private final VerificationTokenPersistenceMapper mapper;

    @Override
    public EmailVerificationToken save(EmailVerificationToken token) {
        var savedToken = repo.save(mapper.fromDomain(token));
        return mapper.fromEntity(savedToken);
    }

    @Override
    public Optional<EmailVerificationToken> findByTokenHash(String tokenHash) {
        return Optional.empty();
    }

    @Override
    public void invalidateUserTokens(UUID userId) {

    }
}
