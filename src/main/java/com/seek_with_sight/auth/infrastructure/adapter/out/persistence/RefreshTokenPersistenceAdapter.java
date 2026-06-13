package com.seek_with_sight.auth.infrastructure.adapter.out.persistence;

import com.seek_with_sight.auth.domain.model.RefreshToken;
import com.seek_with_sight.auth.application.port.out.RefreshTokenPort;
import com.seek_with_sight.auth.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.seek_with_sight.auth.infrastructure.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;

import java.util.Optional;
import java.util.UUID;

public class RefreshTokenPersistenceAdapter
        extends BasePersistenceAdapter<RefreshToken, RefreshTokenEntity, RefreshTokenJpaRepository>
        implements RefreshTokenPort {

    public RefreshTokenPersistenceAdapter(
            RefreshTokenJpaRepository repository,
            PersistenceMapper<RefreshToken, RefreshTokenEntity> mapper
    ) {
        super(repository, mapper, RefreshTokenEntity::new);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(mapper::toDomain);
    }

    @Override
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return repository.findByUserId(userId).map(mapper::toDomain);
    }

    @Override
    public void deleteByToken(String token) {
        repository.deleteByToken(token);
    }
}
