package com.seek_with_sight.shared.infrastructure.config.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.cache.client-config")
public record ClientCacheProperties(
        int staticResourceDuration
) {

}
