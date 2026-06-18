package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import java.util.UUID;

public record ProductImageResponse(
        UUID id,
        String url,
        boolean isPrimary,
        int width,
        int height,
        String altText,
        int sortOrder
) {
}
