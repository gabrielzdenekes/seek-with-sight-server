package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CartResponse(
        UUID id,
        BigDecimal totalPrice,
        String currency,
        List<CartItemResponse> items
) {
}
