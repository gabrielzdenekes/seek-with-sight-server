package com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    @Column(nullable = false, length = 150)
    private String name;

    @Column(unique = true, nullable = false, length = 200)
    private String slug;

    @Column(length = 10000)
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(cascade = CascadeType.ALL)
    @OrderBy("sortOrder ASC")
    private List<CategoryEntity> children = new ArrayList<>();

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
