package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_tags")
public class ProductTagEntity extends BaseEntity {
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 120)
    private String slug;

    @ManyToMany(mappedBy = "tags")
    private List<ProductEntity> products = new ArrayList<>();
}
