package com.seek_with_sight.email.domain.model;

import com.seek_with_sight.domain.model.BaseDomainModel;

import java.time.Instant;
import java.util.UUID;

public class EmailVerificationToken extends BaseDomainModel {
    private UUID userId;
    private String token;
    private Instant expiresAt;
    private boolean used;

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
