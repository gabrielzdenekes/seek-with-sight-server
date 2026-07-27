package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

import java.util.UUID;

public class ProductAlreadyReviewedException extends BusinessException {
    public ProductAlreadyReviewedException(UUID productId, UUID userId) {
        super(
                "PRODUCT_ALREADY_REVIEWED",
                ErrorType.BUSINESS,
                "Product with ID %s is already reviewed by user with ID %s",
                productId,
                userId
        );
    }
}
