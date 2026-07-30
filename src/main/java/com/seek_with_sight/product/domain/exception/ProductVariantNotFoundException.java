package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ProductVariantNotFoundException extends BusinessException {
    public ProductVariantNotFoundException(Object... args) {
        super(ErrorCode.PRODUCT_VARIANT_NOT_FOUND, ErrorType.NOT_FOUND, "Product variant with ID %s not found", args);
    }
}
