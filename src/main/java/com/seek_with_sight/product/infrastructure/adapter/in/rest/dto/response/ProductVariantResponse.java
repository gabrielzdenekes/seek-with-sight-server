package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record ProductVariantResponse(
        UUID id,
        String title,
        String sku,
        String barcode,
        BigDecimal price,
        BigDecimal compareAtPrice,
        boolean isActive,
        int sortOrder,
        BigDecimal weight,
        String weightUnit,
        String dimensionUnit,
        BigDecimal length,
        BigDecimal width,
        BigDecimal height,
        List<ProductVariantOptionResponse> selectedOptions,
        List<ImageResponse> images
) {
}
