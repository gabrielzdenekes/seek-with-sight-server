package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import java.util.UUID;

public record BrandResponse(
        UUID id,
        String name,
        String slug,
        String description,
        String logoUrl,
        String websiteUrl
) {
}
