package com.seek_with_sight.cart.infrastructure.config;

import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.CartPersistenceAdapter;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper.CartPersistenceMapper;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartBeanConfig {
    @Bean
    public CartRepositoryPort cartRepositoryPort(
            CartJpaRepository repo,
            CartPersistenceMapper mapper
    ) {
        return new CartPersistenceAdapter(repo, mapper);
    }
}
