package com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    @EntityGraph(attributePaths = {
            "items.variant",
            "items.product"
    })
    List<OrderEntity> findByStatusAndCreatedAtBefore(OrderStatus status, Instant createdAtBefore);
}
