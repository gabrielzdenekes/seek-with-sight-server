package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

public record BrandResponse(
        String name,
        String slug,
        String description,
        String logoUrl,
        String websiteUrl
) {
}
