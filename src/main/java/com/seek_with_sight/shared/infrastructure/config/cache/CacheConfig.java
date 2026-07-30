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

@Configuration
@EnableCaching
@EnableConfigurationProperties({ CacheProperties.class, ClientCacheProperties.class })
public class CacheConfig {
    @Bean
    public CacheManager caffeineCacheManager(CacheProperties cacheProperties) {
        var cacheManager = new CaffeineCacheManager(
                CacheNames.PRODUCTS,
                CacheNames.CART,
                CacheNames.USER,
                CacheNames.CATEGORIES_TREE
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

        var config = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(
                        RedisSerializationContext
                                .SerializationPair
                                .fromSerializer(serializer)
                )
                .disableCachingNullValues()
                .entryTtl(Duration.ofHours(cacheProperties.entryTtl()));

        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(config)
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
