package com.seek_with_sight.product.application.port.in.review;

import com.seek_with_sight.product.domain.model.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface GetProductReviewsUseCase {
    Page<ProductReview> get(UUID productId, Pageable pageable);
}
