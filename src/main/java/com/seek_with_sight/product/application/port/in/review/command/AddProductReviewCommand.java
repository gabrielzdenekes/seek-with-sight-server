package com.seek_with_sight.product.application.port.in.review.command;

public record AddProductReviewCommand(
        Integer rating,
        String title,
        String comment
) {
}
