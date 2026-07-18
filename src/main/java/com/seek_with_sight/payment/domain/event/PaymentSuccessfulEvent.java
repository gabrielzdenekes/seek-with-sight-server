package com.seek_with_sight.payment.domain.event;

import com.seek_with_sight.shared.domain.event.DomainEvent;

import java.util.UUID;

public class PaymentSuccessfulEvent extends DomainEvent {
    private UUID orderId;

    public PaymentSuccessfulEvent(UUID orderId) {
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }
}
