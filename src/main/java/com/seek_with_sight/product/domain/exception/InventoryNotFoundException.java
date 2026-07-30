package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class InventoryNotFoundException extends BusinessException {
    public InventoryNotFoundException(Object... args) {
        super(ErrorCode.INVENTORY_NOT_FOUND, ErrorType.NOT_FOUND, "Inventory for variant with ID %s not found", args);
    }
}
