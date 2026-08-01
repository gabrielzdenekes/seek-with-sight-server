package com.seek_with_sight.order.domain.model.dto;

import java.math.BigDecimal;
import java.util.UUID;

public interface BestSellingVariant {
    UUID getVariantId();
    String getVariantTitle();
    String getProductName();
    String getSku();
    BigDecimal getPrice();
    BigDecimal getSalePrice();
    String getImageUrl();
    Long getTotalSold();
}
