package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

public record ProductImageDto(
        String url,
        Boolean isPrimary,
        Integer width,
        Integer height,
        String altText,
        Integer sortOrder
) {
}
