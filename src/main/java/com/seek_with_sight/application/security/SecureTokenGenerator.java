package com.seek_with_sight.application.security;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class SecureTokenGenerator {
    private static final SecureRandom secureRandom =
            new SecureRandom();

    public String generate() {
        var bytes = new byte[32];

        secureRandom.nextBytes(bytes);

        return Base64.getUrlEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }
}
