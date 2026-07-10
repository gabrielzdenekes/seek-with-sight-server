package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class InventoryNotFoundException extends BusinessException {
    public InventoryNotFoundException(Object[] args) {
        super("INVENTORY_NOT_FOUND", "inventory.error.not-found", ErrorType.NOT_FOUND, args);
    }
}
