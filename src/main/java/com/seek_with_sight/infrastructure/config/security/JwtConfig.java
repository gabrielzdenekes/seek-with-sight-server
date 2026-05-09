package com.seek_with_sight.infrastructure.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.security.jwt")
public record JwtConfig(
        String secretKey,
        int accessTokenExpiration,
        int refreshTokenExpiration,
        String refreshCookiePath,
        String refreshCookieSameSite
) {
}
