package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.command.AddProductReviewCommand;
import com.seek_with_sight.product.domain.model.ProductReview;

import java.util.UUID;

public interface AddProductReviewUseCase {
    ProductReview add(UUID productId, AddProductReviewCommand command);
}
