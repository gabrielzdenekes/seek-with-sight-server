package com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_variant_option")
public class ProductVariantOptionEntity extends BaseEntity {
    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 1000)
    private String value;

    @Column(name = "sort_order")
    private Integer sortOrder;
}
