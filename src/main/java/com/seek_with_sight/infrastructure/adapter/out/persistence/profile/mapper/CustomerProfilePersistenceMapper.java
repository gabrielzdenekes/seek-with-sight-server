package com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper;

import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.entity.CustomerProfileJpaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerProfilePersistenceMapper {
    CustomerProfile fromEntity(CustomerProfileJpaEntity entity);

    void updateEntityFromDomain(
            CustomerProfile domain,
            @MappingTarget CustomerProfileJpaEntity entity
    );
}
