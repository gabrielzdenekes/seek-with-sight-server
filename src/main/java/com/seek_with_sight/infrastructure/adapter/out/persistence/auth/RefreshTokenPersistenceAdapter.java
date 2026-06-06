package com.seek_with_sight.infrastructure.adapter.out.persistence.auth;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.application.port.out.security.RefreshTokenPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.entity.RefreshTokenEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.repository.RefreshTokenJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BasePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;

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
