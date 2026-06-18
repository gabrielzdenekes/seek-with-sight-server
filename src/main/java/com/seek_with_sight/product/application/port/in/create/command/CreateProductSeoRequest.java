package com.seek_with_sight.product.application.port.in.create.command;

public record CreateProductSeoRequest(
        String metaTitle,

        String metaDescription,

        String canonicalUrl,

        String ogTitle,

        String ogDescription,

        String ogImageUrl
) {
}
