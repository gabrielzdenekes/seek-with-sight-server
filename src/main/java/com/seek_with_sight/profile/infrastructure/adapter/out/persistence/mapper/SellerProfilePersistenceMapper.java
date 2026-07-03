package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
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
public interface SellerProfilePersistenceMapper extends PersistenceMapper<SellerProfile, SellerProfileEntity> {
    @Override
    SellerProfile toDomain(SellerProfileEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    SellerProfileEntity toEntity(SellerProfile domain, @Context CycleAvoidingMappingContext context);

    @Override
    void updateEntityFromDomain(
            SellerProfile domain,
            @MappingTarget SellerProfileEntity entity,
            @Context CycleAvoidingMappingContext context);
}
