package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    @Override
    @EntityGraph(attributePaths = {
            "brand",
            "category"
    })
    Optional<ProductEntity> findById(UUID uuid);
}
