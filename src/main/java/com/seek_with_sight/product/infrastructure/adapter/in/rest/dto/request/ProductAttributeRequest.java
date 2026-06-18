package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductAttributeRequest(
        @NotBlank(message = "{product.attribute.name.required}")
        @Size(max = 150, message = "{product.attribute.name.max-length}")
        String name,

        @NotBlank(message = "{product.attribute.value.required}")
        @Size(max = 500, message = "{product.attribute.value.max-length}")
        String value,

        Boolean isFilterable,

        Integer sortOrder
) {
}
