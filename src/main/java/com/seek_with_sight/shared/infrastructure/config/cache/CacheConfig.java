package com.seek_with_sight.shared.infrastructure.config.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
@EnableCaching
@EnableConfigurationProperties(CacheProperties.class)
public class CacheConfig {
    @Bean
    public CacheManager cacheManager(CacheProperties cacheProperties) {
        var cacheManager = new CaffeineCacheManager(
                CacheNames.PRODUCTS,
                CacheNames.CART,
                CacheNames.USER
        );

        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .initialCapacity(cacheProperties.initialCapacity())
                        .maximumSize(cacheProperties.maximumSize())
                        .expireAfterWrite(Duration.ofMinutes(cacheProperties.expireAfterWrite()))
                        .recordStats()
        );

        return cacheManager;
    }
}
