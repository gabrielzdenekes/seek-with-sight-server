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
@Table(name = "product_images")
public class ProductImageEntity extends BaseEntity {
    @Column(nullable = false, length = 2048)
    private String url;

    @Column(name = "thumbnail_url", length = 2048)
    private String thumbnailUrl;

    @Column(name = "alt_text", length = 300)
    private String altText;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Column
    private Integer width;

    @Column
    private Integer height;
}
