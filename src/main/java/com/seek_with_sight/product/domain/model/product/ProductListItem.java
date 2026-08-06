package com.seek_with_sight.product.domain.model.product;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record ProductListItem(
        UUID id,
        String name,
        BigDecimal price,
        BigDecimal salePrice,
        String imageUrl,
        Integer discountPercentage,
        Instant saleEndDate
) {
}
