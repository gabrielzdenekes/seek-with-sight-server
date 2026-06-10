package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity;

import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.BaseEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer_profiles")
@Getter
@Setter
@NoArgsConstructor
public class CustomerProfileEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private String phone;
    private String shippingAddress;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private UserEntity user;
}
