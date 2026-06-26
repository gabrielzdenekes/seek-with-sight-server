package com.seek_with_sight.shared.infrastructure.config.cache;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application.cache.config")
public record CacheProperties(
        int initialCapacity,
        int maximumSize,
        int expireAfterWrite
) {

}
