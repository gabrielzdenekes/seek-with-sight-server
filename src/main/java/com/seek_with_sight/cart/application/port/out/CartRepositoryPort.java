package com.seek_with_sight.cart.application.port.out;

import com.seek_with_sight.cart.domain.model.Cart;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface CartRepositoryPort extends BaseRepositoryPort<Cart> {
    Optional<Cart> findWithItemsByUserId(UUID userId);

    boolean existsByUserId(Long userId);
}
