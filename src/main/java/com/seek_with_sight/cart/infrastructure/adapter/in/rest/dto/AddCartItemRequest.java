package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddCartItemRequest(
        @NotNull(message = "{add-cart-item.product-id.required}")
        UUID productId,

        @Min(value = 1, message = "{add-cart-item.quantity.min}")
        @Max(value = 99, message = "{add-cart-item.quantity.max}")
        int quantity
) {
}
