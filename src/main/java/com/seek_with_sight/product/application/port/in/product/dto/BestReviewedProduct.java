package com.seek_with_sight.product.application.port.in.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface BestReviewedProduct {
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
