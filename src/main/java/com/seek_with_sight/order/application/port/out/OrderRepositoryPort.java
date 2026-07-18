package com.seek_with_sight.order.application.port.out;

import com.seek_with_sight.order.domain.model.Order;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepositoryPort extends BaseRepositoryPort<Order> {
    Optional<Order> findById(UUID orderId);
}
