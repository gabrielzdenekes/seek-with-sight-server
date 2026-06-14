package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

public record ProductSeoDto(
        String metaTitle,
        String metaDescription,
        String canonicalUrl,
        String ogTitle,
        String ogDescription,
        String ogImageUrl
) {
}
