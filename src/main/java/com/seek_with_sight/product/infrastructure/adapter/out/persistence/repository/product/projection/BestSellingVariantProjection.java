package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.product.projection;

import java.math.BigDecimal;
import java.util.UUID;

public interface BestSellingVariantProjection {
    UUID getId();
    UUID getVariantId();
    String getVariantTitle();
    String getProductName();
    String getSku();
    BigDecimal getPrice();
    BigDecimal getSalePrice();
    String getImageUrl();
    Long getTotalSold();
}
