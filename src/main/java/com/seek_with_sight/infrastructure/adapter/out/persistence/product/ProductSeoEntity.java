package com.seek_with_sight.infrastructure.adapter.out.persistence.product;

import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "product_seo")
public class ProductSeoEntity extends BaseEntity {
    @Column(name = "meta_title", length = 70)
    private String metaTitle;

    @Column(name = "meta_description", length = 170)
    private String metaDescription;

    @Column(name = "canonical_url", length = 500)
    private String canonicalUrl;

    @Column(name = "og_title", length = 200)
    private String ogTitle;

    @Column(name = "og_description", length = 300)
    private String ogDescription;

    @Column(name = "og_image_url", length = 2048)
    private String ogImageUrl;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
}
