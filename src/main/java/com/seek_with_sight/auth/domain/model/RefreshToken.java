package com.seek_with_sight.auth.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import com.seek_with_sight.user.domain.model.User;

import java.time.LocalDateTime;

public class RefreshToken extends BaseDomainModel {
    private String token;

    private User user;

    private LocalDateTime expiresAt;

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
