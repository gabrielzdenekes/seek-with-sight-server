package com.seek_with_sight.product.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

public class ProductVariantOption extends BaseDomainModel {
    private String name;

    private String value;

    private Integer sortOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
