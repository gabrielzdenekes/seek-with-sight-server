package com.seek_with_sight.cart.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ItemAlreadyAddedToCartException extends BusinessException {
    public ItemAlreadyAddedToCartException(Object... args) {
        super(
                ErrorCode.ITEM_ALREADY_ADDED_TO_CART,
                ErrorType.BUSINESS,
                "Variant with ID %s already added to the cart",
                args
        );
    }
}
