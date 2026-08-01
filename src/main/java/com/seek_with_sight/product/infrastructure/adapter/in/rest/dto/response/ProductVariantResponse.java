package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record ProductVariantResponse(
        UUID id,
        String title,
        String sku,
        List<ImageResponse> images,
        BigDecimal salePrice,
        Instant saleStartDate,
        Instant saleEndDate
) {
}
