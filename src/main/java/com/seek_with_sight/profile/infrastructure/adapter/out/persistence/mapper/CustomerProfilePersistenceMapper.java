package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CustomerProfilePersistenceMapper
        extends PersistenceMapper<CustomerProfile, CustomerProfileEntity> {
    @Override
    CustomerProfile toDomain(CustomerProfileEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    CustomerProfileEntity toEntity(CustomerProfile domain, @Context CycleAvoidingMappingContext context);

    @Override
    void updateEntityFromDomain(
            CustomerProfile domain,
            @MappingTarget CustomerProfileEntity entity,
            @Context CycleAvoidingMappingContext context);
}
