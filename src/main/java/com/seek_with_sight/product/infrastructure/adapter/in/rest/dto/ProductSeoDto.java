package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProductSeoDto(
        @Size(max = 70, message = "{product.seo.meta-title.max-length}")
        String metaTitle,

        @Size(max = 170, message = "{product.seo.meta-description.max-length}")
        String metaDescription,

        @Size(max = 500, message = "{product.seo.canonical-url.max-length}")
        @Pattern(
                regexp = "^(https?://).+$",
                message = "{product.seo.canonical-url.format}"
        )
        String canonicalUrl,

        @Size(max = 200, message = "{product.seo.og-title.max-length}")
        String ogTitle,

        @Size(max = 300, message = "{product.seo.og-description.max-length}")
        String ogDescription,

        @Size(max = 2048, message = "{product.seo.og-image-url.max-length}")
        @Pattern(
                regexp = "^(https?://).+$",
                message = "{product.seo.og-image-url.format}"
        )
        String ogImageUrl
) {
}
