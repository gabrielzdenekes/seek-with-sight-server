package com.seek_with_sight.email.infrastructure.out.persistence.repository;

import com.seek_with_sight.email.infrastructure.out.persistence.entity.EmailVerificationTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface VerificationTokenJpaRepository extends JpaRepository<EmailVerificationTokenEntity, UUID> {
    Optional<EmailVerificationTokenEntity> findByToken(String token);

    List<EmailVerificationTokenEntity> findAllByUserId(UUID userId);
}
