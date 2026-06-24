package com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "cart_items")
public class CartItemEntity extends BaseEntity {
    @Column(nullable = false)
    private UUID productId;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "currency_code", length = 3, nullable = false)
    private String currencyCode;

    @Column(precision = 19, scale = 4)
    private BigDecimal price;
}
