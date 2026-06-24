package com.seek_with_sight.cart.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class CartItemNotFoundException extends BusinessException {
    public CartItemNotFoundException(Object[] args) {
        super("CART_ITEM_NOT_FOUND", "update-cart-item.error.item-not-found", ErrorType.BUSINESS_RULE, args);
    }
}
