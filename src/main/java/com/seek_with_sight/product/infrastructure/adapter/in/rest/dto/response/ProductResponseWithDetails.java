package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseWithDetails extends ProductResponse {
    private CategoryResponse category;

    private BrandResponse brand;

    private ProductSeoResponse seo;

    private List<ProductImageResponse> images;

    private List<ProductAttributeResponse> attributes;
}
