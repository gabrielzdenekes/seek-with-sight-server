package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

public record ProductVariantOptionResponse(
        String name,
        String value,
        Integer sortOrder
) {
}
