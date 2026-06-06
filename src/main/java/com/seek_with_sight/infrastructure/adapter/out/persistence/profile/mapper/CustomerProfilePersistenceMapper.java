package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper;

import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.CustomerProfileJpaEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerProfilePersistenceMapper
        extends PersistenceMapper<CustomerProfile,  CustomerProfileJpaEntity> {
    @Override
    CustomerProfile toDomain(CustomerProfileJpaEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(CustomerProfile domain, @MappingTarget CustomerProfileJpaEntity entity);
}
