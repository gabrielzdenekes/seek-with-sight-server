package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public record AddProductReviewRequest(
        @Min(1)
        @Max(5)
        Integer rating,

        @Size(max = 150)
        String title,

        @Size(max = 1000)
        String comment
) {}
