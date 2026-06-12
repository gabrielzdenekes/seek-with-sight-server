package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity;

import com.seek_with_sight.profile.domain.model.SellerStatus;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "seller_profiles")
@Getter
@Setter
@NoArgsConstructor
public class SellerProfileEntity extends BaseEntity {
    private String businessName;
    private String businessAddress;
    private String taxId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private SellerStatus status;
}
