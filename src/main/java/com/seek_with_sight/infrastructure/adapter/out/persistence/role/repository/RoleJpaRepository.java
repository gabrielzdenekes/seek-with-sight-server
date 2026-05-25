package com.seek_with_sight.infrastructure.adapter.out.persistence.role.repository;

import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RoleName name);
}
