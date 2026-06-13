package com.seek_with_sight.authentication.application.port.out;

import com.seek_with_sight.authentication.domain.model.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenPort {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(UUID userId);

    void deleteByToken(String token);

    RefreshToken save(RefreshToken refreshToken);
}
