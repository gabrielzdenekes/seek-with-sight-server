package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.product.domain.model.Product;

public record ProductSeoDto(
        Product product,
        String metaTitle,
        String metaDescription,
        String canonicalUrl,
        String ogTitle,
        String ogDescription,
        String ogImageUrl
) {
}
