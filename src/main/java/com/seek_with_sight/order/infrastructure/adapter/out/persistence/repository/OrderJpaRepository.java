package com.seek_with_sight.order.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.domain.model.dto.BestSellingVariant;
import com.seek_with_sight.order.infrastructure.adapter.out.persistence.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderJpaRepository extends JpaRepository<OrderEntity, UUID> {
    @EntityGraph(attributePaths = {
            "items.variant",
            "items.product"
    })
    List<OrderEntity> findByStatusAndCreatedAtBefore(OrderStatus status, Instant createdAtBefore);

    @Override
    @EntityGraph(attributePaths = {
            "items.variant",
            "items.product"
    })
    Optional<OrderEntity> findById(UUID variantId);

    @Query(
            value = """
            SELECT 
                v.id AS variantId,
                v.title AS variantTitle,
                p.name AS productName,
                v.sku AS sku,
                v.price AS price,
                v.salePrice AS salePrice,
                COALESCE(vImg.url, pImg.url) AS imageUrl,
                SUM(i.quantity) AS totalSold
            FROM OrderItemEntity i
            JOIN i.order o
            JOIN i.variant v
            JOIN v.product p
            LEFT JOIN v.images vi ON vi.sortOrder = 0
            LEFT JOIN vi.image vImg
            LEFT JOIN p.images pi ON pi.sortOrder = 0
            LEFT JOIN pi.image pImg
            WHERE p.status = 'ACTIVE'
              AND o.status = 'PROCESSING' OR o.status = 'SHIPPED' OR o.status = 'DELIVERED'
            GROUP BY 
                v.id, v.title, p.name, v.sku, v.price, v.salePrice, vImg.url, pImg.url
            ORDER BY totalSold DESC
            """
    )
    Page<BestSellingVariant> findBestSellingVariants(Pageable pageable);
}
