package com.seek_with_sight.infrastructure.adapter.out.persistence.product;

import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_variant_option_values")
public class ProductVariantOptionValueEntity extends BaseEntity {
    @Column(nullable = false, length = 150)
    private String value;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_id", nullable = false)
    private ProductVariantOptionEntity option;
}
