package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ProductNotFoundException extends BusinessException {
    public ProductNotFoundException() {
        this(new Object[0]);
    }

    public ProductNotFoundException(Object... args) {
        super("PRODUCT_NOT_FOUND", ErrorType.NOT_FOUND, "Product with ID %s not found", args);
    }
}
