package com.seek_with_sight.product.domain;

import com.seek_with_sight.shared.domain.event.DomainEvent;

import java.util.UUID;

public class ProductCreatedEvent extends DomainEvent {
    private final UUID productId;

    public ProductCreatedEvent(UUID productId) {
        super();
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
