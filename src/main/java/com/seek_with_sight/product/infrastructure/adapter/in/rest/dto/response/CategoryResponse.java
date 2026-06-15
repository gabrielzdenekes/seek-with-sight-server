package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

public record CategoryResponse(
        String name,
        String slug,
        String description,
        String imageUrl
) {
}
