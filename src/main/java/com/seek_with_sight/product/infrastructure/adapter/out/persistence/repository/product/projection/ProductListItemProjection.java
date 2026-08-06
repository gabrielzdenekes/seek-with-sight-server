package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface ProductListItemProjection {
    UUID getId();
    String getName();
    BigDecimal getPrice();
    BigDecimal getSalePrice();
    String getImageUrl();
    Integer getDiscountPercentage();
    Instant getSaleEndDate();
}
