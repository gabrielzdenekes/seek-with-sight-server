package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record UpdateItemQuantityRequest(
        @Min(value = 1)
        @Max(value = 99)
        int quantity
) {
}
