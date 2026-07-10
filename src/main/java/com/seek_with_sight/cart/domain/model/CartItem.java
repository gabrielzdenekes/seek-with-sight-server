package com.seek_with_sight.cart.domain.model;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.shared.domain.model.BaseDomainModel;

import java.math.BigDecimal;

public class CartItem extends BaseDomainModel {
    private Product product;

    private Cart cart;

    private ProductVariant variant;

    private Integer quantity;

    private BigDecimal price;

    private String currencyCode = "EUR";

    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
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

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
