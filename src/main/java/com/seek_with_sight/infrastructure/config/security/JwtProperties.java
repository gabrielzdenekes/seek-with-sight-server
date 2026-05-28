package com.seek_with_sight.infrastructure.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.security.jwt")
public record JwtProperties(
        String secretKey,
        int accessTokenExpiration,
        int refreshTokenExpiration,
        String refreshCookiePath
) {
}
