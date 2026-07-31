package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.application.port.in.product.dto.ProductListItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, UUID> {
    @Override
    @EntityGraph(attributePaths = {
            "brand",
            "category",
            "images",
            "variants"
    })
    Optional<ProductEntity> findById(UUID uuid);

    @Query("""
            SELECT DISTINCT p
            FROM ProductEntity p
            JOIN p.variants v
            WHERE p.status = 'ACTIVE'
              AND v.salePrice IS NOT NULL
              AND :now BETWEEN v.saleStartDate AND v.saleEndDate
            ORDER BY v.discountPercentage DESC
            """)
    Page<ProductListItem> findTopDiscountedProducts(@Param("now") Instant now, Pageable pageable);
}
