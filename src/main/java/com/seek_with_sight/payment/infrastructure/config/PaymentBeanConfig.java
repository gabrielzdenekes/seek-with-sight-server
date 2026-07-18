package com.seek_with_sight.payment.infrastructure.config;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.payment.application.port.in.CreatePaymentIntentUseCase;
import com.seek_with_sight.payment.application.service.CreatePaymentIntentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentBeanConfig {
    @Bean
    public CreatePaymentIntentUseCase createPaymentIntentUseCase(
            OrderRepositoryPort orderRepo
    ) {
        return new CreatePaymentIntentService(orderRepo);
    }
}
