package com.seek_with_sight.order.infrastructure.adapter.out.persistence.event;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.order.domain.model.PaymentStatus;
import com.seek_with_sight.payment.domain.event.PaymentSuccessfulEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class OrderPayedEventListener {
    private final OrderRepositoryPort orderRepositoryPort;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handlePaymentSuccessful(PaymentSuccessfulEvent event) {
        var orderId = event.getOrderId();
        var order = orderRepositoryPort.findById(orderId).get();

        order.setPaymentStatus(PaymentStatus.PAID);

        orderRepositoryPort.save(order);
    }
}
