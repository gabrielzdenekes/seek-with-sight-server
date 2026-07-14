package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductReviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductReviewJpaRepository extends JpaRepository<ProductReviewEntity, UUID> {
    Page<ProductReviewEntity> findByProductId(UUID productId, Pageable pageable);

    boolean existsByProductIdAndUserId(UUID productId, UUID userId);
}
