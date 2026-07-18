package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class InvalidQuantityUpdateException extends BusinessException {
    public InvalidQuantityUpdateException(int reservedQuantity, int newQuantity) {
        super(
                "INVALID_QUANTITY_UPDATE",
                ErrorType.BUSINESS,
                "New quantity: %s cannot be less than reserved quantity %s",
                newQuantity,
                reservedQuantity);
    }
}
