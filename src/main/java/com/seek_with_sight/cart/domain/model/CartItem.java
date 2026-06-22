package com.seek_with_sight.cart.domain.model;

import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;
import java.util.UUID;

public class CartItem extends BaseDomainModel {
    private UUID productId;
    private int quantity;
    private BigDecimal priceAtAddition;

    public CartItem(UUID productId, int quantity, BigDecimal priceAtAddition) {
        this.productId = productId;
        this.quantity = quantity;
        this.priceAtAddition = priceAtAddition;
    }

    public void incrementQuantity(int amount) {
        this.quantity += amount;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPriceAtAddition() {
        return priceAtAddition;
    }

    public void setPriceAtAddition(BigDecimal priceAtAddition) {
        this.priceAtAddition = priceAtAddition;
    }
}
