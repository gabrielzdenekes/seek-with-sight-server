package com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository;

import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    Optional<UserJpaEntity> findByEmailIgnoreCase(String email);
}
