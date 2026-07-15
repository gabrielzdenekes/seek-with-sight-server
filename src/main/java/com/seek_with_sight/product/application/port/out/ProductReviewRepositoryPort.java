package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.domain.model.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ProductReviewRepositoryPort {
    Page<ProductReview> findByProductId(UUID productId, Pageable pageable);

    boolean existsByProductIdAndUserId(UUID productId, UUID userId);
}
