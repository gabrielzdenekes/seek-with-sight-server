package com.seek_with_sight.order.domain.model;

import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;

public class OrderItem extends BaseDomainModel {
    private int quantity;

    private String currencyCode;

    private BigDecimal unitPrice;

    private BigDecimal totalPrice;

    private Order order;

    private ProductVariant variant;

    private Product product;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ProductVariant getVariant() {
        return variant;
    }

    public void setVariant(ProductVariant variant) {
        this.variant = variant;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
