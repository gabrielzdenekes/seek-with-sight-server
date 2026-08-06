package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category;

import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategoryTreeProjection;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategorySearchProjection;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, UUID> {
    @EntityGraph(attributePaths = {"children"})
    List<CategoryTreeProjection> findAllByParentIsNullOrderBySortOrderAsc();

    @Query("""
            SELECT c FROM CategoryEntity c
            WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND c.isActive = true
            ORDER BY c.name ASC""")
    List<CategorySearchProjection> searchByName(@Param("name") String name);
}
