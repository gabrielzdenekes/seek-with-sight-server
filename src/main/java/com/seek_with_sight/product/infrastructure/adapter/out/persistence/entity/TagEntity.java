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
@Table(name = "tags")
public class TagEntity extends BaseEntity {
    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 120)
    private String slug;
}
