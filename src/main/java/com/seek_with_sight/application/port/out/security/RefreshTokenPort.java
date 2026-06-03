package com.seek_with_sight.application.port.out.security;

import com.seek_with_sight.domain.model.auth.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenPort {
    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByUserId(UUID userId);

    void deleteByToken(String token);

    RefreshToken save(RefreshToken refreshToken);
}
