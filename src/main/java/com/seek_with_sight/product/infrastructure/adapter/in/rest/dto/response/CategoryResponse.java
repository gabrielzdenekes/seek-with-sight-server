package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import java.util.UUID;

public record CategoryResponse(
        UUID id,
        String name,
        String slug,
        String description,
        String imageUrl
) {
}
