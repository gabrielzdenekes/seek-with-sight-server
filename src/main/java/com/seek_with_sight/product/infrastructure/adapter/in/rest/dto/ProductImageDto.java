package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

public record ProductImageDto(
        String url,
        String altText,
        Integer sortOrder,
        Boolean isPrimary,
        Integer width,
        Integer height
) {
}
