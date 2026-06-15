package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record ProductImageRequest(
        @NotBlank(message = "{product.images.required}")
        @Size(max = 2048, message = "{product.image.url.max-length}")
        @Pattern(
                regexp = "^(https?://).+$",
                message = "{product.image.url.format}"
        )
        String url,

        Boolean isPrimary,

        @NotNull(message = "{product.image.width.required}")
        @Positive(message = "{product.image.width.positive-value}")
        Integer width,

        @NotNull(message = "{product.image.height.required}")
        @Positive(message = "{product.image.height.positive-value}")
        Integer height,

        @Size(max = 300, message = "{product.image.alt-text.max-length}")
        String altText,

        @PositiveOrZero(message = "{product.image.sort-order.positive-or-zero}")
        Integer sortOrder
) {
}
