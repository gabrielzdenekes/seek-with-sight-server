package com.seek_with_sight.infrastructure.adapter.out.persistence.product;

import com.seek_with_sight.domain.model.product.ProductStatus;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "products", indexes = {
        @Index(name = "idx_product_slug", columnList = "slug"),
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_brand", columnList = "brand_id"),
        @Index(name = "idx_product_category", columnList = "category_id")
})
public class ProductEntity extends BaseEntity {
    @Column(nullable = false, length = 300)
    private String name;

    @Column(unique = true, nullable = false, length = 350)
    private String slug;

    @Column(name = "short_description", length = 500)
    private String shortDescription;

    @Column(length = 20000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status;

    @Column(name = "currency_code", length = 3)
    private String currencyCode = "EUR";

    @Column(precision = 10, scale = 3)
    private BigDecimal weight;

    @Column(name = "weight_unit", length = 10)
    private String weightUnit = "kg";

    @Column(name = "requires_shipping")
    private Boolean requiresShipping = true;

    @Column(name = "is_digital")
    private Boolean isDigital = false;

    @Column(name = "tax_class", length = 50)
    private String taxClass = "standard";

    @Column(name = "base_price", precision = 19, scale = 4)
    private BigDecimal basePrice;

    @Column(name = "compare_at_price", precision = 19, scale = 4)
    private BigDecimal compareAtPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<ProductTagEntity> tags = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("sortOrder ASC")
    private List<ProductImageEntity> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariantEntity> variants = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductAttributeEntity> attributes = new ArrayList<>();

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductSeoEntity seo;
}
