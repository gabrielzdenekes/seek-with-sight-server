package com.seek_with_sight.infrastructure.adapter.out.persistence.auth;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.domain.port.out.security.RefreshTokenPort;
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
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        repository.deleteByUserId(userId);
    }
}
