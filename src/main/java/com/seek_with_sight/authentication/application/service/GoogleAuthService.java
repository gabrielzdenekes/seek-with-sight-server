package com.seek_with_sight.authentication.application.service;

import com.seek_with_sight.authentication.application.port.in.GoogleAuthUseCase;
import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleAuthService implements GoogleAuthUseCase {

    @Override
    public JwtLoginData authenticate(String googleAuthToken) {
        return null;
    }
}
