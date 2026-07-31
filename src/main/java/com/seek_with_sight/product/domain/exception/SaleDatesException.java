package com.seek_with_sight.product.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

import java.time.Instant;
import java.util.UUID;

public class SaleDatesException extends BusinessException {
    public SaleDatesException(Instant startDate, Instant endData, UUID variantId) {
        super(
                ErrorCode.INVALID_SALE_DATES,
                ErrorType.BUSINESS,
                "Sale start date %s is after sale end date %s for variant with ID %s",
                startDate,
                endData,
                variantId);
    }
}
