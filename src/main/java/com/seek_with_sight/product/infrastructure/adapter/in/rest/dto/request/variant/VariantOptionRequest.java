package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record VariantOptionRequest(
        @NotBlank(message = "{variant-option.name.required}")
        @Size(min = 1, max = 50, message = "{variant-option.name.size}")
        String name,

        @NotBlank(message = "{variant-option.value.required}")
        @Size(min = 1, max = 100, message = "{variant-option.value.size}")
        String value,

        @Min(value = 0, message = "{variant-option.sort-order.negative}")
        Integer sortOrder
) {
}
