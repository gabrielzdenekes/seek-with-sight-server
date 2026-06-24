package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
        UUID productId,
        String productName,
        String productSlug,
        String productImageUrl,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalPrice
) {
}
