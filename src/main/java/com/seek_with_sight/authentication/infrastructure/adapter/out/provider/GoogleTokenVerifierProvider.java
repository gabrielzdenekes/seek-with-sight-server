package com.seek_with_sight.authentication.infrastructure.adapter.out.provider;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.seek_with_sight.authentication.application.port.out.GoogleTokenVerifierPort;
import com.seek_with_sight.authentication.infrastructure.config.bean.GoogleAuthProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RequiredArgsConstructor
public class GoogleTokenVerifierProvider implements GoogleTokenVerifierPort {
    private final GoogleAuthProperties googleProps;

    @Override
    public GoogleIdToken verify(String authCode) throws GeneralSecurityException, IOException {
        var transport = new NetHttpTransport();
        var jsonFactory = GsonFactory.getDefaultInstance();

        var tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                transport,
                jsonFactory,
                "https://oauth2.googleapis.com/token",
                googleProps.clientId(),
                googleProps.clientSecret(),
                authCode,
                "postmessage"
        )
                .execute();

        var idTokenString = tokenResponse.getIdToken();

        var verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(googleProps.clientId()))
                .build();

        return verifier.verify(idTokenString);
    }
}
