package com.seek_with_sight.order.infrastructure.adapter.in.rest.dto;

import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.domain.model.PaymentStatus;

import java.math.BigDecimal;

public record OrderResponse(
        String orderNumber,
        BigDecimal totalAmount,
        OrderStatus status,
        PaymentStatus paymentStatus
) {
}
