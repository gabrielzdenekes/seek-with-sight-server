package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.application.port.in.product.dto.DiscountedProductListItem;
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

    /**
     * * 1. IMAGE FALLBACK (COALESCE):
     * Joins variant and product primary images (sortOrder = 0). COALESCE prioritizes
     * the variant's primary image URL; if null, it falls back to the product's primary image URL.
     * * 3. TOP DISCOUNT PER PRODUCT & DEDUPLICATION:
     * - Inner Subquery 1: Determines MAX(discountPercentage) for the current product 'p'.
     * - Inner Subquery 2: Takes MIN(v2.id) matching that max discount. This tie-breaker
     * ensures only one variant per product is selected if multiple variants share the same
     * discount rate, avoiding duplicate rows in pagination.
     * * 4. SALE VALIDATION & SORTING:
     * Filters for ACTIVE products with active sales (v.salePrice IS NOT NULL and :now is
     * within the start/end date range). Results are ordered globally by highest discount first.
     * * 5. OPTIMIZED COUNT QUERY:
     * Provides an explicit countQuery using COUNT(DISTINCT p) so Spring Data JPA can
     * calculate total pages efficiently without producing invalid automatic count queries.
     * * @param now      Current timestamp used to validate active sale windows.
     * @param pageable Pagination settings (page number, page size).
     * @return Paginated list of top discounted product items.
     */
    @Query(
            value = """
            SELECT
                p.name AS name,
                v.price AS price,
                v.salePrice AS salePrice,
                v.discountPercentage AS discountPercentage,
                v.saleEndDate AS saleEndDate,
                COALESCE(vImg.url, pImg.url) AS imageUrl
            FROM ProductVariantEntity v
            JOIN v.product p
            LEFT JOIN v.images vi ON vi.sortOrder = 0
            LEFT JOIN vi.image vImg
            LEFT JOIN p.images pi ON pi.sortOrder = 0
            LEFT JOIN pi.image pImg
            WHERE p.status = 'ACTIVE'
              AND v.salePrice IS NOT NULL
              AND :now BETWEEN v.saleStartDate AND v.saleEndDate
              AND v.id = (
                  SELECT MIN(v2.id)
                  FROM ProductVariantEntity v2
                  WHERE v2.product = p
                    AND v2.salePrice IS NOT NULL
                    AND :now BETWEEN v2.saleStartDate AND v2.saleEndDate
                    AND v2.discountPercentage = (
                        SELECT MAX(v3.discountPercentage)
                        FROM ProductVariantEntity v3
                        WHERE v3.product = p
                          AND v3.salePrice IS NOT NULL
                          AND :now BETWEEN v3.saleStartDate AND v3.saleEndDate
                    )
              )
            ORDER BY v.discountPercentage DESC
        """,
            countQuery = """
            SELECT COUNT(DISTINCT p)
            FROM ProductEntity p
            JOIN p.variants v
            WHERE p.status = 'ACTIVE'
              AND v.salePrice IS NOT NULL
              AND :now BETWEEN v.saleStartDate AND v.saleEndDate
        """
    )
    Page<DiscountedProductListItem> findTopDiscountedProducts(@Param("now") Instant now, Pageable pageable);
}
