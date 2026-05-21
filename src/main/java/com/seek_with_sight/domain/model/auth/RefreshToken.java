package com.seek_with_sight.domain.model.auth;

import com.seek_with_sight.domain.model.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class RefreshToken {
    private UUID id;

    private String token;

    private User user;

    private LocalDateTime expiresAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
