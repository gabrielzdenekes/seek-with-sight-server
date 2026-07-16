package com.seek_with_sight.product.application.port.in.product.command;

public record AddProductReviewCommand(
        Integer rating,
        String title,
        String comment
) {
}
