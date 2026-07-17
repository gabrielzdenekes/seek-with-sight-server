package com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
}
