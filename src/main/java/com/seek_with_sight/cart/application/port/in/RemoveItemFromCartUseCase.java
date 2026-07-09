package com.seek_with_sight.cart.application.port.in;

import java.util.UUID;

public interface RemoveItemFromCartUseCase {
    void remove(UUID variantId);
}
