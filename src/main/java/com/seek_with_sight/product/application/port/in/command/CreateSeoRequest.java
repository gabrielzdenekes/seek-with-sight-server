package com.seek_with_sight.product.application.port.in.command;

public record CreateSeoRequest(
        String metaTitle,

        String metaDescription,

        String canonicalUrl,

        String ogTitle,

        String ogDescription,

        String ogImageUrl
) {
}
