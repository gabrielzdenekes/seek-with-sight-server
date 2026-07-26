package com.seek_with_sight.authentication.application.service;

import com.seek_with_sight.authentication.application.port.in.GoogleAuthUseCase;
import com.seek_with_sight.authentication.application.port.out.GoogleTokenVerifierPort;
import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GoogleAuthService implements GoogleAuthUseCase {
    private final GoogleTokenVerifierPort tokenVerifier;

    @Override
    public JwtLoginData authenticate(String googleAuthToken) {
        return null;
    }
}
