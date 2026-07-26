package com.seek_with_sight.authentication.application.port.out;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleTokenVerifierPort {
    GoogleIdToken verify(String idToken) throws GeneralSecurityException, IOException;
}
