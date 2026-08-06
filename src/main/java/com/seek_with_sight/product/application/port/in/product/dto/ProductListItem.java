package com.seek_with_sight.product.application.port.in.product.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface ProductListItem {
    UUID getId();
    String getName();
    BigDecimal getPrice();
    BigDecimal getSalePrice();
    String getImageUrl();
    Integer getDiscountPercentage();
    Instant getSaleEndDate();
}
