package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductVariantEntity;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

public class ProductInventory extends BaseDomainModel {
    private ProductVariantEntity variant;

    private Integer quantity = 0;

    private Integer reservedQuantity = 0;

    public Integer getAvailableStock() {
        return quantity - reservedQuantity;
    }

    public ProductVariantEntity getVariant() {
        return variant;
    }

    public void setVariant(ProductVariantEntity variant) {
        this.variant = variant;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(Integer reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }
}
