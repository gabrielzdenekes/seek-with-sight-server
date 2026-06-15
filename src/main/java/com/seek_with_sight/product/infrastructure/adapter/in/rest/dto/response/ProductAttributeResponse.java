package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import java.util.UUID;

public record ProductAttributeResponse(
        UUID id,
        String name,
        String value
) {
}
