package com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity;

import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "email_verification_tokens")
@Getter
@Setter
public class EmailVerificationTokenEntity extends BaseEntity {
    private UUID userId;

    @Column(nullable = false, unique = true)
    private String token;

    private Instant expiresAt;

    private boolean used;
}
