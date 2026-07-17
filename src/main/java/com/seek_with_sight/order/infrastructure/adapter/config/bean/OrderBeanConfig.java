package com.seek_with_sight.order.infrastructure.adapter.config.bean;

import com.seek_with_sight.cart.application.port.in.FindCartForCurrentUserUseCase;
import com.seek_with_sight.cart.application.port.out.CartRepositoryPort;
import com.seek_with_sight.order.application.port.in.CheckoutUseCase;
import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.order.application.service.CheckoutService;
import com.seek_with_sight.order.application.service.OrderAppMapper;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.OrderPersistenceAdapter;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.mapper.OrderPersistenceMapper;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository.OrderJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderBeanConfig {
    @Bean
    public OrderRepositoryPort orderRepositoryPort(
            OrderJpaRepository repo,
            OrderPersistenceMapper mapper
    ) {
        return new OrderPersistenceAdapter(repo, mapper);
    }

    @Bean
    public CheckoutUseCase checkoutUseCase(
            OrderRepositoryPort orderRepo,
            FindCartForCurrentUserUseCase findCartForCurrentUserUseCase,
            OrderAppMapper mapper,
            CartRepositoryPort cartRepo
    ) {
        return new CheckoutService(orderRepo, findCartForCurrentUserUseCase, mapper, cartRepo);
    }
}
