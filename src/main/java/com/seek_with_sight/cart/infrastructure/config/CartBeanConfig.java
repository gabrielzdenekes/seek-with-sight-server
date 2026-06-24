package com.seek_with_sight.cart.infrastructure.config;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUser;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.application.service.FindCartByUserEmailService;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.CartPersistenceAdapter;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper.CartPersistenceMapper;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
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

    @Bean
    public FindCartForCurrentUser findCartByUserEmailUseCase(
            CartRepositoryPort cartRepo,
            CurrentUserPort currentUserPort
    ) {
        return new FindCartByUserEmailService(currentUserPort, cartRepo);
    }
}
