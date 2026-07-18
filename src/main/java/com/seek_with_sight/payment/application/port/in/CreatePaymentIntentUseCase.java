package com.seek_with_sight.payment.application.port.in;

import java.util.UUID;

public interface CreatePaymentIntentUseCase {
    PaymentIntentResult create(UUID orderId);
}
