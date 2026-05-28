package com.seek_with_sight.infrastructure.config.application;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record ApplicationProperties(
        String baseUrl
) {
}
