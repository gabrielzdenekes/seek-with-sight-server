package com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto;

import java.util.List;
import java.util.UUID;

public record CartResponse(
        UUID id,
        List<CartItemResponse> items
) {
}
