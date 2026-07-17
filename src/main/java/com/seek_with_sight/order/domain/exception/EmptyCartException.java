package com.seek_with_sight.order.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class EmptyCartException extends BusinessException {
    public EmptyCartException() {
        super("EMPTY_CART", ErrorType.BUSINESS, "Cannot create a checkout. Cart is empty", null);
    }
}
