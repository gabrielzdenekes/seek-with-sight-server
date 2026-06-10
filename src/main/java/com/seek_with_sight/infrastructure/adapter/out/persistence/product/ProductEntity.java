package com.seek_with_sight.infrastructure.adapter.out.persistence.product;

import com.seek_with_sight.domain.model.product.ProductStatus;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products", indexes = {
        @Index(name = "idx_product_slug", columnList = "slug"),
        @Index(name = "idx_product_status", columnList = "status"),
        @Index(name = "idx_product_brand", columnList = "brand_id"),
        @Index(name = "idx_product_category", columnList = "category_id")
})
public class ProductEntity extends BaseEntity {
    private String name;

    private String slug;

    private String shortDescription;

    private String description;

    private ProductStatus status;

    private String currencyCode;

    private BigDecimal weight;

    private String weightUnit;

    private Boolean requiresShipping;

    private Boolean isDigital;

    private String taxClass;

    private BigDecimal basePrice;

    private BigDecimal compareAtPrice;

    private ProductCategoryEntity category;

    private BrandEntity brand;

    private List<ProductImageEntity> images = new ArrayList<>();

    private List<ProductVariantEntity> variants = new ArrayList<>();

    private List<ProductAttributeEntity> attributes = new ArrayList<>();

    private ProductSeoEntity seo;
}
