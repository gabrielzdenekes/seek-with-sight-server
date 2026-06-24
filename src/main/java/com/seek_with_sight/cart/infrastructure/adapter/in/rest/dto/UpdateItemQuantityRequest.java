package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UpdateItemQuantityRequest(
        @Min(value = 1, message = "{update-cart-item.quantity.min}")
        @Max(value = 99, message = "{update-cart-item.quantity.max}")
        int quantity
) {
}
