package com.seek_with_sight.payment.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.Name;

@ConfigurationProperties(prefix = "stripe")
public record StripeProperties(
       @Name("api.key") String apiKey,
       @Name("webhook.secret") String webhookSecret
) {
}
