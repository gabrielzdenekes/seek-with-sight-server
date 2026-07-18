package com.seek_with_sight.payment.infrastructure.config;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.payment.application.port.in.CreatePaymentIntentUseCase;
import com.seek_with_sight.payment.application.service.CreatePaymentIntentService;
import com.stripe.Stripe;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(StripeProperties.class)
public class PaymentBeanConfig {
    public PaymentBeanConfig(StripeProperties stripeProps) {
        Stripe.apiKey = stripeProps.apiKey();
    }

    @Bean
    public CreatePaymentIntentUseCase createPaymentIntentUseCase(
            OrderRepositoryPort orderRepo
    ) {
        return new CreatePaymentIntentService(orderRepo);
    }
}
