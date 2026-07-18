package com.seek_with_sight.order.application.port.in;

import com.seek_with_sight.order.domain.model.Order;

public interface CheckoutUseCase {
    Order checkout();
}
