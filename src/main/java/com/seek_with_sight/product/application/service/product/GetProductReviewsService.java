package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.GetProductReviewsUseCase;
import com.seek_with_sight.product.application.port.out.ProductReviewRepositoryPort;
import com.seek_with_sight.product.domain.model.ProductReview;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductReviewsService implements GetProductReviewsUseCase {
    private final ProductReviewRepositoryPort reviewsRepo;

    @Override
    @Transactional
    public Page<ProductReview> get(UUID productId, Pageable pageable) {
        return reviewsRepo.findByProductId(productId, pageable);
    }
}
