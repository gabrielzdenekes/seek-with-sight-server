package com.seek_with_sight.payment.infrastructure.adapter.in.rest;

import com.seek_with_sight.payment.application.port.in.CreatePaymentIntentUseCase;
import com.seek_with_sight.payment.application.port.in.PaymentIntentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final CreatePaymentIntentUseCase createPaymentIntentUseCase;

    @PostMapping("/create-intent/{orderId}")
    public PaymentIntentResult createIntent(UUID orderId) {
        return createPaymentIntentUseCase.create(orderId);
    }
}
