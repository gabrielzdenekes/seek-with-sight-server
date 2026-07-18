package com.seek_with_sight.payment.application.service;

import com.seek_with_sight.payment.application.service.exception.StripeHookFailedException;
import com.seek_with_sight.payment.domain.event.PaymentSuccessfulEvent;
import com.seek_with_sight.payment.infrastructure.config.StripeProperties;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import com.stripe.model.PaymentIntent;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StripeHookHandler {
    private static final String SUCCESS_EVENT_TYPE = "payment_intent.succeeded";

    private final StripeProperties stripeProps;
    private final DomainEventPublisher publisher;

    public void handle(String payload, String signature) {
        try {
            var event = Webhook.constructEvent(payload, signature, stripeProps.webhookSecret());

            if (event.getType().equals(SUCCESS_EVENT_TYPE)) {
                var paymentObject = event.getDataObjectDeserializer().getObject();

                if (paymentObject.isPresent()) {
                    var intent = (PaymentIntent) paymentObject.get();
                    var orderId = UUID.fromString(intent.getMetadata().get("order_id"));

                    publisher.publish(
                            new PaymentSuccessfulEvent(orderId)
                    );
                }
            }
        } catch (Exception e) {
            throw new StripeHookFailedException(e);
        }
    }
}
