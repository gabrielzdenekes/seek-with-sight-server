package com.seek_with_sight.product.application.port.in.review;

import com.seek_with_sight.product.application.port.in.review.command.AddProductReviewCommand;
import com.seek_with_sight.product.domain.model.ProductReview;

import java.util.UUID;

public interface AddProductReviewUseCase {
    ProductReview add(UUID productId, AddProductReviewCommand command);
}
