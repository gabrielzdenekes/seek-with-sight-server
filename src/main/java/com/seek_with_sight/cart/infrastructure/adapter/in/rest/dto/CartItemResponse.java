package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;

import java.math.BigDecimal;
import java.util.UUID;

public record CartItemResponse(
        ProductResponse product,
        ProductVariantResponse variant,
        int quantity,
        String currencyCode,
        BigDecimal totalPrice
) {
}
