package com.seek_with_sight.authentication.infrastructure.adapter.out.persistence;

import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.authentication.application.port.out.RefreshTokenPort;
import com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.mapper.RefreshTokenPersistenceMapper;
import com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.repository.RefreshTokenJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;

import java.util.Optional;
import java.util.UUID;

public class RefreshTokenPersistenceAdapter
        extends BasePersistenceAdapter<
        RefreshToken,
        RefreshTokenEntity,
        RefreshTokenJpaRepository,
        RefreshTokenPersistenceMapper>
        implements RefreshTokenPort {

    public RefreshTokenPersistenceAdapter(
            RefreshTokenJpaRepository repository,
            RefreshTokenPersistenceMapper mapper
    ) {
        super(repository, mapper, RefreshTokenEntity::new);
    }

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository
                .findByToken(token)
                .map(e -> mapper.toDomain(e));
    }

    @Override
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return repository
                .findByUserId(userId)
                .map(e -> mapper.toDomain(e));
    }

    @Override
    public void deleteByToken(String token) {
        repository.deleteByToken(token);
    }
}
