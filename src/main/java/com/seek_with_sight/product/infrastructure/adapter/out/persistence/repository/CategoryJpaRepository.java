package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.application.port.in.category.CategoryListItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryListItem> findAllProjectedBy();
}
