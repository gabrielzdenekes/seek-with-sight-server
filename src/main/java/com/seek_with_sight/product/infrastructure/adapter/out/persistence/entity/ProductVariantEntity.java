package com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_variants")
public class ProductVariantEntity extends BaseEntity {
    @Column(nullable = false, length = 300)
    private String title;

    @Column(unique = true, nullable = false, length = 100)
    private String sku;

    @Column(length = 100)
    private String barcode;

    @Column(name = "price", precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "compare_at_price", precision = 19, scale = 4)
    private BigDecimal compareAtPrice;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(precision = 10, scale = 3)
    private BigDecimal weight;

    @Column(name = "weight_unit", length = 10)
    private String weightUnit;

    @Column(name = "dimension_unit", length = 10)
    private String dimensionUnit;

    @Column(precision = 10, scale = 2)
    private BigDecimal length;

    @Column(precision = 10, scale = 2)
    private BigDecimal width;

    @Column(precision = 10, scale = 2)
    private BigDecimal height;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantOptionEntity> selectedOptions;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImageEntity> images;
}
