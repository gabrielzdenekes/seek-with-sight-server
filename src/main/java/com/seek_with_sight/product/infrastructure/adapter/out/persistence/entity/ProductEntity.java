package com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.media.infrastructure.out.persistence.entity.ImageEntity;
import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.domain.model.ProductStatus;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "products", indexes = {
        @Index(name = "idx_product_slug", columnList = "slug", unique = true),
        @Index(name = "idx_product_status", columnList = "status")
})
public class ProductEntity extends BaseEntity {
    @Column(nullable = false, length = 300)
    private String name;

    @Column(nullable = false, unique = true, length = 180)
    private String slug;

    @Column(length = 500)
    private String shortDescription;

    @Lob
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status = ProductStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false) // Usa nullable = true si el producto puede no tener categoría
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private BrandEntity brand;

//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ProductVariantEntity> variants = new ArrayList<>();
}
