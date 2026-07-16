package com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_variants")
public class ProductVariantEntity extends BaseEntity {
    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, unique = true, length = 100)
    private String sku;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal price;

    @Column(name = "sale_price", precision = 19, scale = 4)
    private BigDecimal salePrice;

    @Column(name = "sale_start_date")
    private Instant saleStartDate;

    @Column(name = "sale_end_date")
    private Instant saleEndDate;

    @Column(name = "discount_percentage")
    private Integer discountPercentage = 0;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "variant")
    private List<ProductImageEntity> images = new ArrayList<>();

    public void setImages(List<ProductImageEntity> images) {
        this.images.clear();

        if (images != null) {
            this.images.addAll(images);
        }
    }

    @PrePersist
    @PreUpdate
    public void calculateDiscountPercentage() {
        if (salePrice != null && salePrice.compareTo(price) < 0) {
            // ((price - salePrice) / price) * 100
            var discount = price.subtract(salePrice)
                    .divide(price, 2, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"));

            discountPercentage = discount.intValue();
        } else {
            salePrice = null;
            discountPercentage = 0;
        }
    }
}
