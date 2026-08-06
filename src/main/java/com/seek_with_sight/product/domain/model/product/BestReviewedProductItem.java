package com.seek_with_sight.product.domain.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public record BestReviewedProductItem(
        UUID id,

        String name,

        String slug,

        Double averageRating,

        Integer reviewCount,

        BigDecimal price,

        BigDecimal salePrice,

        Integer discountPercentage,

        String imageUrl
) {
}
