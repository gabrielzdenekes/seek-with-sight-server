package com.seek_with_sight.email.infrastructure.out.persistence;

import com.seek_with_sight.email.domain.model.EmailVerificationToken;
import com.seek_with_sight.email.application.port.out.VerificationTokenRepositoryPort;
import com.seek_with_sight.email.infrastructure.out.persistence.entity.EmailVerificationTokenEntity;
import com.seek_with_sight.email.infrastructure.out.persistence.mapper.VerificationTokenPersistenceMapper;
import com.seek_with_sight.email.infrastructure.out.persistence.repository.VerificationTokenJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

import java.util.Optional;
import java.util.UUID;

public class VerificationTokenRepositoryAdapter
        extends BasePersistenceAdapter<
        EmailVerificationToken,
        EmailVerificationTokenEntity,
        VerificationTokenJpaRepository,
        VerificationTokenPersistenceMapper>
        implements VerificationTokenRepositoryPort {

    public VerificationTokenRepositoryAdapter(
            VerificationTokenJpaRepository repository,
            VerificationTokenPersistenceMapper mapper
    ) {
        super(repository, mapper, EmailVerificationTokenEntity::new);
    }

    @Override
    public Optional<EmailVerificationToken> findByToken(String rawToken) {
        return repository.findByToken(rawToken).map(e -> mapper.toDomain(e));
    }

    @Override
    public void invalidateUserTokens(UUID userId) {
        var tokens = repository.findAllByUserId(userId);

        for (var tok : tokens) {
            tok.setUsed(true);
        }

        repository.saveAll(tokens);
    }
}
