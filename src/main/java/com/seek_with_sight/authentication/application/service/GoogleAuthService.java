package com.seek_with_sight.authentication.application.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.seek_with_sight.authentication.application.port.in.GoogleAuthUseCase;
import com.seek_with_sight.authentication.application.port.out.GoogleTokenVerifierPort;
import com.seek_with_sight.authentication.application.port.out.JwtTokenPort;
import com.seek_with_sight.authentication.domain.exception.ExternalAuthProviderVerificationException;
import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import com.seek_with_sight.profile.application.port.in.CreateCustomerProfileUseCase;
import com.seek_with_sight.profile.application.port.in.command.CreateCustomerProfileCommand;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;

@RequiredArgsConstructor
public class GoogleAuthService implements GoogleAuthUseCase {
    private static final String GOOGLE_PROVIDER_NAME = "Google";

    private final GoogleTokenVerifierPort tokenVerifier;
    private final CreateCustomerProfileUseCase createCustomerProfileUseCase;
    private final JwtTokenPort jwtTokenPort;
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public JwtLoginData authenticate(String idCode) {
        GoogleIdToken idToken;
        
        try {
            idToken = tokenVerifier.verify(idCode);
        } catch (Exception e) {
            throw new ExternalAuthProviderVerificationException(e, GOOGLE_PROVIDER_NAME);
        }
        
        if (idToken == null) {
            throw new ExternalAuthProviderVerificationException(GOOGLE_PROVIDER_NAME);
        }

        var user = getUser(idToken.getPayload());
        var accessToken = jwtTokenPort.generateAccessToken(user);
        var refreshToken = jwtTokenPort.generateRefreshToken(user);
        var loginData = new JwtLoginData(
                accessToken,
                refreshToken,
                user
        );

        return loginData;
    }

    private User getUser(GoogleIdToken.Payload payload) {
        var userResult = userRepositoryPort.findByEmailIgnoreCase(payload.getEmail());

        if (userResult.isPresent()) {
            return userResult.get();
        }

        var command = getProfileCommand(payload);
        var user = createCustomerProfileUseCase.createCustomerProfile(command);

        return user;
    }

    private static @NonNull CreateCustomerProfileCommand getProfileCommand(GoogleIdToken.Payload payload) {
        var command = new CreateCustomerProfileCommand(
                payload.getEmail(),
                null,
                (String) payload.get("given_name"),
                (String) payload.get("family_name"),
                null,
                payload.getEmailVerified()
        );

        return command;
    }
}
