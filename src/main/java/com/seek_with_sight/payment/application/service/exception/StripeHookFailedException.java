package com.seek_with_sight.payment.application.service.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class StripeHookFailedException extends BusinessException {
    public StripeHookFailedException(Throwable cause) {
        super(
                cause,
                ErrorCode.STRIPE_HOOK_FAILED,
                ErrorType.BUSINESS,
                "Stripe hook handler failed"
        );
    }
}
