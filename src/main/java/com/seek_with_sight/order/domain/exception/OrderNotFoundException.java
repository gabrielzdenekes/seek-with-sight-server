package com.seek_with_sight.order.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

import java.util.UUID;

public class OrderNotFoundException extends BusinessException {

    public OrderNotFoundException(UUID orderId) {
        super(ErrorCode.ORDER_NOT_FOUND, ErrorType.NOT_FOUND, "Order with ID %s was not found", orderId);
    }
}
