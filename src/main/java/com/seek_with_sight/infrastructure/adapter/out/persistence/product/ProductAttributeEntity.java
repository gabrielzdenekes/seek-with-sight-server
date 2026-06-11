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
@Table(name = "product_attributes")
public class ProductAttributeEntity extends BaseEntity {
    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 500)
    private String value;

    @Column(name = "unit", length = 50)
    private String unit;

    @Column(name = "is_filterable")
    private Boolean isFilterable = false;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
