package com.seek_with_sight.cart.infrastructure.config;

import com.seek_with_sight.cart.application.port.in.AddItemToCartUseCase;
import com.seek_with_sight.cart.application.port.in.ClearCartUseCase;
import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.in.RemoveItemFromCartUseCase;
import com.seek_with_sight.cart.application.port.in.UpdateItemQuantityUseCase;
import com.seek_with_sight.cart.application.service.AddItemToCartService;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.cart.application.service.ClearCartService;
import com.seek_with_sight.cart.application.service.FindCartByUserEmailService;
import com.seek_with_sight.cart.application.service.RemoveItemFromCartService;
import com.seek_with_sight.cart.application.service.UpdateItemQuantityService;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.CartPersistenceAdapter;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.mapper.CartPersistenceMapper;
import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository.CartJpaRepository;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
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
    public FindCartForCurrentUserUseCase findCartByUserEmailUseCase(
            CartRepositoryPort cartRepo,
            CurrentUserPort currentUserPort
    ) {
        return new FindCartByUserEmailService(currentUserPort, cartRepo);
    }

    @Bean
    public AddItemToCartUseCase addItemToCartUseCase(
            CurrentUserPort currentUserPort,
            ProductRepositoryPort productRepo,
            CartRepositoryPort cartRepo,
            FindCartForCurrentUserUseCase findCartForCurrentUserUseCase
    ) {
        return new AddItemToCartService(
                productRepo,
                cartRepo,
                findCartForCurrentUserUseCase
        );
    }

    @Bean
    public UpdateItemQuantityUseCase updateItemQuantityUseCase(
            CurrentUserPort currentUserPort,
            CartRepositoryPort cartRepo
    ) {
        return new UpdateItemQuantityService(cartRepo, currentUserPort);
    }

    @Bean
    public RemoveItemFromCartUseCase removeItemFromCartUseCase(
            CurrentUserPort currentUserPort,
            CartRepositoryPort cartRepo
    ) {
        return new RemoveItemFromCartService(currentUserPort, cartRepo);
    }

    @Bean
    public ClearCartUseCase clearCartUseCase(
            CurrentUserPort currentUserPort,
            CartRepositoryPort cartRepo
    ) {
        return new ClearCartService(currentUserPort, cartRepo);
    }
}
