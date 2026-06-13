package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CustomerProfilePersistenceMapper
        extends PersistenceMapper<CustomerProfile, CustomerProfileEntity> {
    @Override
    CustomerProfile toDomain(CustomerProfileEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(CustomerProfile domain, @MappingTarget CustomerProfileEntity entity);
}
