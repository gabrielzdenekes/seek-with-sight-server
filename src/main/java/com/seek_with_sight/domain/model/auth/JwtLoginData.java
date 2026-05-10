package com.seek_with_sight.domain.model.auth;

import com.seek_with_sight.domain.model.user.User;

public class JwtLoginData {
    private String accessToken;

    private User user;

    public JwtLoginData(String accessToken, User user) {
        this.accessToken = accessToken;
        this.user = user;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
