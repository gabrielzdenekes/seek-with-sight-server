package com.seek_with_sight.domain.port.out.security;

import com.seek_with_sight.domain.model.auth.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenPort {
    Optional<RefreshToken> findByToken(String token);

    void deleteById(UUID id);

    void deleteByUserId(UUID userId);
}
