package com.seek_with_sight.product.domain.events;

import com.seek_with_sight.shared.domain.event.DomainEvent;

import java.util.UUID;

public class ProductUpdatedEvent extends DomainEvent {
    private final UUID productId;

    public ProductUpdatedEvent(UUID productId) {
        this.productId = productId;
    }

    public UUID getProductId() {
        return productId;
    }
}
