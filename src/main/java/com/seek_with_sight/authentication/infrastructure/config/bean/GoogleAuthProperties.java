package com.seek_with_sight.authentication.infrastructure.config.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "google")
public record GoogleAuthProperties(
        String clientId
) {
}
