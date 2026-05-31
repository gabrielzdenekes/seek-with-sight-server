package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper;

import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileJpaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfilePersistenceMapper {
    SellerProfile formEntity(SellerProfileJpaEntity entity);
}
