package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.product.application.port.in.brand.BrandSearchItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BrandJpaRepository extends JpaRepository<BrandEntity, UUID> {
    @Query("""
            SELECT b FROM BrandEntity b
            WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :name, '%'))
            AND b.isActive = true
            ORDER BY b.name ASC""")
    List<BrandSearchItem> searchByName(@Param("name") String name);
}
