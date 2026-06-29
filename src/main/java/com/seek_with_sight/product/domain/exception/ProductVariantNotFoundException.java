package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ProductVariantNotFoundException extends BusinessException {
    public ProductVariantNotFoundException(Object[] args) {
        super("PRODUCT_VARIANT_NOT_FOUND", "product-variant.error.not-found", ErrorType.BUSINESS_RULE, args);
    }
}
