package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

public record VariantOptionRequest(
        String name,
        String value,
        Integer sortOrder
) {
}
