package com.seek_with_sight.cart.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Cart extends BaseDomainModel {
    private final UUID userId;
    private final Map<UUID, CartItem> items;

    public Cart(UUID userId) {
        this.userId = userId;
        this.items = new HashMap<>();
    }

    public void addProduct(UUID productId, int quantity, BigDecimal price) {
        items.compute(productId, (id, existingItem) -> {
            if (existingItem == null) {
                return new CartItem(productId, quantity, price);
            }

            return existingItem.incrementQuantity(quantity);
        });
    }

    public void removeProduct(UUID productId) {
        items.remove(productId);
    }

    public void updateQuantity(UUID productId, int quantity) {
        var existingProduct = items.get(productId);
        if (existingProduct != null) {
            items.put(productId, new CartItem(productId, quantity, existingProduct.priceAtAddition()));
        }
    }

    public BigDecimal calculateTotal() {
        return items.values().stream()
                .map(item -> item.priceAtAddition().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getUserId() {
        return userId;
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items.values());
    }
}
