package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import java.util.UUID;

public record ProductSeoResponse(
        UUID id,
        String metaTitle,
        String metaDescription,
        String canonicalUrl,
        String ogTitle,
        String ogDescription,
        String ogImageUrl
) {
}
