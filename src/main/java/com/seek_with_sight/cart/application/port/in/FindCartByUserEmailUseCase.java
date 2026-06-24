package com.seek_with_sight.cart.application.port.in;

import com.seek_with_sight.cart.domain.model.Cart;

public interface FindCartByUserEmailUseCase {
    Cart findByUserEmail(String email);
}
