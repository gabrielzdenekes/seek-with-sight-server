package com.seek_with_sight.cart.application.port.in;

import java.util.UUID;

public interface UpdateItemQuantityUseCase {
    void update(UUID productId, int quantity);
}
