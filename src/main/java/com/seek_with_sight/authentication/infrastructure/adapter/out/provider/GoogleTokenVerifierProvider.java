package com.seek_with_sight.authentication.infrastructure.adapter.out.provider;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.seek_with_sight.authentication.application.port.out.GoogleTokenVerifierPort;
import com.seek_with_sight.authentication.infrastructure.config.bean.GoogleAuthProperties;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

public class GoogleTokenVerifierProvider implements GoogleTokenVerifierPort {
    private final GoogleAuthProperties googleProps;
    private final GoogleIdTokenVerifier verifier;

    public GoogleTokenVerifierProvider(GoogleAuthProperties googleProps) {
        this.googleProps = googleProps;

        this.verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), GsonFactory.getDefaultInstance())
                .setAudience(Collections.singletonList(googleProps.clientId()))
                .build();
    }

    @Override
    public GoogleIdToken verify(String idToken) throws GeneralSecurityException, IOException {
        return verifier.verify(idToken);
    }
}
