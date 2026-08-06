package com.seek_with_sight.product.domain.model.product;

import java.math.BigDecimal;
import java.util.UUID;

public record BestReviewedProductItem(
        UUID getId,

        String getName,

        String getSlug,

        Double getAverageRating,

        Integer getReviewCount,

        BigDecimal getPrice,

        BigDecimal getSalePrice,

        Integer getDiscountPercentage,

        String getImageUrl
) {
}
