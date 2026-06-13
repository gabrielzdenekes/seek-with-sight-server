package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SellerProfilePersistenceMapper extends PersistenceMapper<SellerProfile, SellerProfileEntity> {
    @Override
    SellerProfile toDomain(SellerProfileEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(SellerProfile domain, @MappingTarget SellerProfileEntity entity);
}
