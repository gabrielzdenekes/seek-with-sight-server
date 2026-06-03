package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper;

import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SellerProfilePersistenceMapper {
    SellerProfile fromEntity(SellerProfileJpaEntity entity);

    void updateEntityFromDomain(
            SellerProfile domain,
            @MappingTarget SellerProfileJpaEntity entity
    );
}
