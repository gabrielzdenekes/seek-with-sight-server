package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.review;

import java.time.LocalDateTime;
import java.util.UUID;

public record ProductReviewResponse(
        UUID id,
        UUID userId,
        Integer rating,
        String title,
        String comment,
        LocalDateTime createdAt
) {
}
