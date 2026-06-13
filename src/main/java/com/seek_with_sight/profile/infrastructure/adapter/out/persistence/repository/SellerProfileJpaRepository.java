package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellerProfileJpaRepository extends JpaRepository<SellerProfileEntity, UUID> {
    Optional<SellerProfileEntity> findByUserEmail(String email);
}
