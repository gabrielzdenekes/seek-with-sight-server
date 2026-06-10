package com.seek_with_sight.domain.model.product;

import com.seek_with_sight.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.List;

public class Product extends BaseDomainModel {
    private String name;

    private String slug;

    private String shortDescription;

    private String description;

    private ProductStatus status;

    private String currencyCode;

    private BigDecimal weight;

    private String weightUnit;

    private Boolean requiresShipping;

    private Boolean isDigital;

    private String taxClass;

    private ProductCategory category;

    private Brand brand;

    private List<ProductImage> images;

    private List<ProductVariant> variants;

    private List<ProductAttribute> attributes;

    private ProductSEO seo;
}
