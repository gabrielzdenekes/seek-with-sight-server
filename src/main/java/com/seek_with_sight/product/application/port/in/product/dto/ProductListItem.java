package com.seek_with_sight.product.application.port.in.product.dto;

import java.math.BigDecimal;
import java.time.Instant;

public interface ProductListItem {
    String getName();
    BigDecimal getPrice();
    BigDecimal getSalePrice();
    String getImageUrl();
    Integer getDiscountPercentage();
    Instant getSaleEndDate();
}
