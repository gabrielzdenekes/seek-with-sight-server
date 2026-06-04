package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository;

import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.CustomerProfileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerProfileJpaRepository extends JpaRepository<CustomerProfileJpaEntity, UUID> {
    Optional<CustomerProfileJpaEntity> findByUserId(UUID userId);
}
