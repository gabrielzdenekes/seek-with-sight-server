package com.seek_with_sight.infrastructure.adapter.out.persistence.product;

import com.seek_with_sight.domain.model.product.Product;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToMany
    @JoinTable(
            name = "variant_selected_options",
            joinColumns = @JoinColumn(name = "product_variant_id"),
            inverseJoinColumns = @JoinColumn(name = "variant_option_value_id")
    )
    private List<ProductVariantOptionValueEntity> selectedOptions;

    @OneToMany(mappedBy = "variant")
    private List<ProductImageEntity> images;
}
