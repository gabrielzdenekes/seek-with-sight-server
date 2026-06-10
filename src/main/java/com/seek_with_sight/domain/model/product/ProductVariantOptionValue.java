package com.seek_with_sight.domain.model.product;

import com.seek_with_sight.domain.model.BaseDomainModel;

public class ProductVariantOptionValue extends BaseDomainModel {
    private ProductVariantOption option;

    private String value;

    private Integer sortOrder;

    public ProductVariantOption getOption() {
        return option;
    }

    public void setOption(ProductVariantOption option) {
        this.option = option;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
