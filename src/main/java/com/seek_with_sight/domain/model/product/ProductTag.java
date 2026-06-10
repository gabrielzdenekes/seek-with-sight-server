package com.seek_with_sight.domain.model.product;

import com.seek_with_sight.domain.model.BaseDomainModel;

import java.util.List;

public class ProductTag extends BaseDomainModel {
    private String name;

    private String slug;

    private List<Product> products;
}
