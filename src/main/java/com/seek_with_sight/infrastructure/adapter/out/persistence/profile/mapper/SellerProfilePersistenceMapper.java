package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper;

import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.SellerProfileJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface SellerProfilePersistenceMapper extends PersistenceMapper<SellerProfile, SellerProfileJpaEntity> {
    @Override
    SellerProfile toDomain(SellerProfileJpaEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(SellerProfile domain, @MappingTarget SellerProfileJpaEntity entity);
}
