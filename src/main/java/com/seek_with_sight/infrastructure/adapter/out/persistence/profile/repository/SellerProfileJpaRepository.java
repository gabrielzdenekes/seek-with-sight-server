package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository;

import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerProfileJpaRepository extends JpaRepository<SellerProfileJpaEntity, UUID> {
    Optional<SellerProfileJpaEntity> findByUserEmail(String email);
}
