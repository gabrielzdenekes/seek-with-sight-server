package com.seek_with_sight.cart.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItem(
        UUID productId,
        int quantity,
        BigDecimal priceAtAddition
) {
    public CartItem incrementQuantity(int amount) {
        return new CartItem(
                productId,
                quantity + amount,
                priceAtAddition
        );
    }
}
