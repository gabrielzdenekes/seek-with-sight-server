package com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity;

import com.seek_with_sight.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "idx_users_email", columnList = "email")
})
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "pass_hash", nullable = false, length = 60)
    private String passHash;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> roles = new HashSet<>();
}
