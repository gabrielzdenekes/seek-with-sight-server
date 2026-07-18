package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.inventory;

import jakarta.validation.constraints.Min;

public record UpdateInventoryRequest(
        @Min(1)
        int quantity
) {
}
