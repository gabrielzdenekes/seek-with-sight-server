package com.seek_with_sight.cart.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.cart.infrastructure.adapter.out.persistence.entity.CartEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CartJpaRepository extends JpaRepository<CartEntity, UUID> {
    Optional<CartEntity> findByUserId(UUID userId);

    @EntityGraph(attributePaths = {
            "items.variant",
            "items.product"
    })
    Optional<CartEntity> findWithItemsByUserId(UUID userId);

    boolean existsByUserId(Long userId);

    @EntityGraph(attributePaths = {
            "items.variant"
    })
    List<CartEntity> findALlByUpdatedAtBefore(Instant updatedBefore);
}
