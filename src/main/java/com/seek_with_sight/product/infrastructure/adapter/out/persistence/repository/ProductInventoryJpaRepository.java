package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductInventoryEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductInventoryJpaRepository extends JpaRepository<ProductInventoryEntity, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT i FROM ProductInventoryEntity i WHERE i.variant.id = :variantId")
    Optional<ProductInventoryEntity> findByVariantIdForUpdate(UUID variantId);
}
