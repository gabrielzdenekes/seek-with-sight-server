package com.seek_with_sight.cart.domain.model;

import com.seek_with_sight.cart.domain.exception.CartItemNotFoundException;
import com.seek_with_sight.cart.domain.exception.ItemAlreadyAddedToCartException;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;
import com.seek_with_sight.user.domain.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Cart extends BaseDomainModel {
    private User user;

    private List<CartItem> items = new ArrayList<>();

    private BigDecimal totalPrice = BigDecimal.valueOf(0.0);

    private String currency;

    public void addItem(CartItem item) {
        var existingItem = items.stream()
                .filter(i -> i.getVariant().getId().equals(item.getVariant().getId()))
                .findFirst();

        if (existingItem.isPresent()) {
            throw new ItemAlreadyAddedToCartException(item.getVariant().getId());
        }

        items.add(item);
        recalculateTotal();
    }

    public void updateItemQuantity(UUID variantId, int quantity) {
        var item = findItemByVariantId(variantId)
                .orElseThrow(() -> new CartItemNotFoundException("variantId=" + variantId));

        item.setQuantity(quantity);
        recalculateTotal();
    }

    public void removeItem(UUID variantId) {
        items.removeIf(i -> i.getVariant().getId().equals(variantId));
        recalculateTotal();
    }

    public void clear() {
        items.clear();
        recalculateTotal();
    }

    public Optional<CartItem> findItemByVariantId(UUID variantId) {
        return items.stream()
                .filter(item -> item.getVariant().getId().equals(variantId))
                .findFirst();
    }

    private void recalculateTotal() {
        this.totalPrice = items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
