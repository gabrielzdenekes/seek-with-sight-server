package com.seek_with_sight.shared.infrastructure.config.cache;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.seek_with_sight.shared.infrastructure.config.cache.multilevel.MultilevelCacheManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

@Configuration
@EnableCaching
@EnableConfigurationProperties({CacheProperties.class, ClientCacheProperties.class})
public class CacheConfig {
    @Bean
    public CacheManager caffeineCacheManager(CacheProperties cacheProperties) {
        var cacheManager = new CaffeineCacheManager();

        var defaultSpec = String.format(
                "initialCapacity=%d,maximumSize=%d,expireAfterWrite=%dm,recordStats",
                cacheProperties.initialCapacity(),
                cacheProperties.maximumSize(),
                cacheProperties.expireAfterWrite()
        );

        for (var cacheName : List.of(CacheNames.PRODUCTS, CacheNames.CART, CacheNames.USER)) {
            cacheManager.registerCustomCache(
                    cacheName,
                    Caffeine.from(defaultSpec).build()
            );
        }

        cacheManager.registerCustomCache(
                CacheNames.CATEGORIES_TREE,
                Caffeine.newBuilder()
                        .initialCapacity(10)
                        .maximumSize(100)
                        .expireAfterWrite(Duration.ofDays(7))
                        .recordStats()
                        .build()
        );

        return cacheManager;
    }

    @Bean
    CacheManager redisCacheManager(
            RedisConnectionFactory connectionFactory,
            CacheProperties cacheProperties
    ) {
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.activateDefaultTyping(
                objectMapper.getPolymorphicTypeValidator(),
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
        );

        var serializer = new GenericJackson2JsonRedisSerializer(objectMapper);

        var defaultConfig = RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair.fromSerializer(serializer)
                )
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(cacheProperties.entryTtl()));

        var customConfigurations = new HashMap<String, RedisCacheConfiguration>();
        customConfigurations.put(
                CacheNames.CATEGORIES_TREE,
                defaultConfig.entryTtl(Duration.ofDays(30))
        );

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(defaultConfig)
                .withInitialCacheConfigurations(customConfigurations)
                .build();
    }

    @Bean
    @Primary
    public CacheManager cacheManager(
            @Qualifier("caffeineCacheManager") CacheManager caffeine,
            @Qualifier("redisCacheManager") CacheManager redis) {

        return new MultilevelCacheManager(caffeine, redis);
    }
}
