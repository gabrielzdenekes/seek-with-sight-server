package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResponseWithDetails extends ProductResponse {
    private CategoryResponse category;

    private BrandResponse brand;

    private List<ImageResponse> images;

    private List<ProductVariantResponse> variants;
}
