package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class InsufficientStockException extends BusinessException {
    public InsufficientStockException(Object[] args) {
        super("INSUFFICIENT_STOCK", "inventory.error.insufficient-stock", ErrorType.BUSINESS_RULE, args);
    }
}
