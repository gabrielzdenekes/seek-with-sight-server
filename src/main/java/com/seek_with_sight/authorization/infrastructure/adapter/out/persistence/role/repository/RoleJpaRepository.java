package com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.repository;

import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, UUID> {
    Optional<RoleEntity> findByName(RoleName name);

    List<RoleEntity> findByNameIn(List<RoleName> roleNames);
}
