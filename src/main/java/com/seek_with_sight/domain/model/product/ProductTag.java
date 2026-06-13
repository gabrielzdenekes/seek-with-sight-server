package com.seek_with_sight.domain.model.product;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.util.List;

public class ProductTag extends BaseDomainModel {
    private String name;

    private String slug;

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
