package com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository;

import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByEmailIgnoreCase(String email);
}
