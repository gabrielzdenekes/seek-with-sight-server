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
@Table(name = "attributes")
public class AttributeEntity extends BaseEntity {
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
}
