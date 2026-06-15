package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import com.seek_with_sight.product.domain.model.ProductStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class FullProductResponse {
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

    private BigDecimal basePrice;

    private BigDecimal compareAtPrice;

    private CategoryResponse category;

    private BrandResponse brand;

    private ProductSeoResponse seo;

    private List<ProductImageResponse> images;

    private List<ProductAttributeResponse> attributes;
}
