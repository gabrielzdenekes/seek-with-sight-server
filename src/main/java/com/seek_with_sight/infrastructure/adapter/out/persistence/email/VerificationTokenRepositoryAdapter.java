package com.seek_with_sight.infrastructure.adapter.out.persistence.email;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.application.port.out.email.VerificationTokenRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity.EmailVerificationTokenEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.repository.VerificationTokenJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BasePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;

import java.util.Optional;
import java.util.UUID;

public class VerificationTokenRepositoryAdapter
        extends BasePersistenceAdapter<
        EmailVerificationToken,
        EmailVerificationTokenEntity,
        VerificationTokenJpaRepository>
        implements VerificationTokenRepositoryPort {

    public VerificationTokenRepositoryAdapter(
            VerificationTokenJpaRepository repository,
            PersistenceMapper<EmailVerificationToken, EmailVerificationTokenEntity> mapper
    ) {
        super(repository, mapper, EmailVerificationTokenEntity::new);
    }

    @Override
    public Optional<EmailVerificationToken> findByToken(String rawToken) {
        return repository.findByToken(rawToken).map(mapper::toDomain);
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
