package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record TagRequest(
        @NotBlank(message = "{tag.name.required}")
        @Size(min = 2, max = 100, message = "{tag.name.length}")
        String name,

        @NotBlank(message = "{tag.slug.required}")
        @Size(message = "{tag.slug.length}")
        @Pattern(
                regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
                message = "{tag.slug.format}"
        )
        String slug
) {
}
