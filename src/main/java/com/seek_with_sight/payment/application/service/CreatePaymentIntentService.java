package com.seek_with_sight.payment.application.service;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.order.domain.exception.OrderNotFoundException;
import com.seek_with_sight.payment.application.port.in.CreatePaymentIntentUseCase;
import com.seek_with_sight.payment.application.port.in.PaymentIntentResult;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class CreatePaymentIntentService implements CreatePaymentIntentUseCase {
    private final OrderRepositoryPort orderRepo;

    @Override
    @Transactional
    public PaymentIntentResult create(UUID orderId) {
        var order = orderRepo.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
        var totalAmount = order.getTotalAmount()
                .multiply(new BigDecimal("100"))
                .longValue();

        try {
            var params = PaymentIntentCreateParams.builder()
                    .setAmount(totalAmount)
                    .setCurrency("eur")
                    .putMetadata("order_id", orderId.toString())
                    .build();

            var intent = PaymentIntent.create(params);

            return new PaymentIntentResult(intent.getClientSecret());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
