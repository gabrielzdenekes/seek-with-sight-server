package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface BestReviewedProductProjection {
    UUID getId();

    String getName();

    String getSlug();

    Double getAverageRating();

    Integer getReviewCount();

    BigDecimal getPrice();

    BigDecimal getSalePrice();

    Integer getDiscountPercentage();

    String getImageUrl();
}
