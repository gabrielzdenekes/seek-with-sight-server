package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddCartItemRequest(
        @NotNull
        UUID productId,

        @NotNull
        UUID productVariantId,

        @Min(value = 1)
        @Max(value = 99)
        int quantity
) {
}
