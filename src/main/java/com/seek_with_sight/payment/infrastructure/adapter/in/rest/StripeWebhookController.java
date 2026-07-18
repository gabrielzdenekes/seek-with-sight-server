package com.seek_with_sight.payment.infrastructure.adapter.in.rest;

import com.seek_with_sight.payment.application.service.StripeHookHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/webhooks")
@RequiredArgsConstructor
public class StripeWebhookController {
    private final StripeHookHandler hookHandler;

    @PostMapping("/stripe")
    public void handleStripeHook(
            @RequestBody String payload,
            @RequestHeader("Stripe-Signature") String signature
    ) {
        hookHandler.handle(payload, signature);
    }
}
