package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository;

import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerProfileJpaRepository extends JpaRepository<CustomerProfileEntity, UUID> {
    Optional<CustomerProfileEntity> findByUserEmail(String email);
}
