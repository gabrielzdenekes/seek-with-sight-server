package com.seek_with_sight.infrastructure.adapter.out.persistence.auth;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.application.port.out.security.RefreshTokenPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.entity.RefreshTokenEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.mapper.RefreshTokenPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.repository.RefreshTokenJpaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class RefreshTokenPersistenceAdapter implements RefreshTokenPort {
    private final RefreshTokenJpaRepository repository;
    private final RefreshTokenPersistenceMapper mapper;

    @Override
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(mapper::fromEntity);
    }

    @Override
    public Optional<RefreshToken> findByUserId(UUID userId) {
        return repository.findByUserId(userId).map(mapper::fromEntity);
    }

    @Override
    public void deleteByToken(String token) {
        repository.deleteByToken(token);
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        var tokenEntity = refreshToken.getId() != null ?
                repository.findById(refreshToken.getId()).orElseThrow() :
                new RefreshTokenEntity();

        mapper.updateEntityFromDomain(refreshToken, tokenEntity);

        var savedEntity = repository.save(tokenEntity);
        return mapper.fromEntity(savedEntity);
    }
}
