package com.seek_with_sight.infrastructure.adapter.out.persistence.permission.repository;

import com.seek_with_sight.infrastructure.adapter.out.persistence.permission.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PermissionJpaRepository extends JpaRepository<PermissionEntity, UUID> {
    Optional<PermissionEntity> findByName(String name);
}
