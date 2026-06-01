package com.seek_with_sight.infrastructure.adapter.out.persistence.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.application.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity.EmailVerificationTokenEntity;
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
        var tokenEntity = token.getId() != null ?
                repo.findById(token.getId()).orElseThrow() :
                new EmailVerificationTokenEntity();

        mapper.updateEntityFromDomain(token, tokenEntity);

        var savedEntity = repo.save(tokenEntity);

        return mapper.fromEntity(savedEntity);
    }

    @Override
    public Optional<EmailVerificationToken> findByToken(String rawToken) {
        return repo.findByToken(rawToken).map(mapper::fromEntity);
    }

    @Override
    public void invalidateUserTokens(UUID userId) {
        var tokens = repo.findAllByUserId(userId);

        for (var tok : tokens) {
            tok.setUsed(true);
        }

        repo.saveAll(tokens);
    }
}
