package com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BaseEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
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
    @Column(unique = true, length = 512)
    private String token;

    @Column()
    private LocalDateTime expiresAt;

    @OneToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
