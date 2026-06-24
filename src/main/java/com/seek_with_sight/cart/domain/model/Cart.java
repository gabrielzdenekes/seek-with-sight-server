package com.seek_with_sight.cart.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Cart extends BaseDomainModel {
    private UUID userId;

    private List<CartItem> items = new ArrayList<>();

    private BigDecimal totalPrice;

    public void addItem(CartItem item) {
        items.add(item);
        recalculateTotal();
    }

    public void updateItemQuantity(UUID productId, int quantity) {
        var item = findItemByProductId(productId).orElseThrow();
        item.setQuantity(quantity);
    }

    private Optional<CartItem> findItemByProductId(UUID productId) {
        return items.stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();
    }

    private void recalculateTotal() {
        this.totalPrice = items.stream()
                .map(CartItem::getSubTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
