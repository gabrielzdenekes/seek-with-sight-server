package com.seek_with_sight.order.infrastructure.adapter.config.bean;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
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
}
