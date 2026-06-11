package com.seek_with_sight.infrastructure.adapter.out.persistence.auth.entity;

import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "refresh_tokens")
public class RefreshTokenEntity extends BaseEntity {
    @Column(unique = true, nullable = false, length = 512)
    private String token;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
