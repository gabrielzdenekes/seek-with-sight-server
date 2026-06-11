package com.seek_with_sight.domain.model.product;

import com.seek_with_sight.domain.model.BaseDomainModel;

import java.util.List;

public class ProductVariantOption extends BaseDomainModel {
    private String name;

    private Product product;

    private Integer sortOrder;

    private List<ProductVariantOptionValue> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public List<ProductVariantOptionValue> getValues() {
        return values;
    }

    public void setValues(List<ProductVariantOptionValue> values) {
        this.values = values;
    }
}
