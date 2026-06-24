package com.seek_with_sight.cart.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ItemAlreadyAddedToCartException extends BusinessException {
    public ItemAlreadyAddedToCartException(Object[] args) {
        super("ITEM_ALREADY_ADDED_TO_CART", "add-cart-item.error.product-already-added", ErrorType.BUSINESS_RULE, args);
    }
}
