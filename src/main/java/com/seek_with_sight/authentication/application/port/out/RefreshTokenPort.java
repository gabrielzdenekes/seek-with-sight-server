package com.seek_with_sight.authentication.application.port.out;

import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenPort extends BaseRepositoryPort<RefreshToken> {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(UUID userId);

    void deleteByToken(String token);
}
