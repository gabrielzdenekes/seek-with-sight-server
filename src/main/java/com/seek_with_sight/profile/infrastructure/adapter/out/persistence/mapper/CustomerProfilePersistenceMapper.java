package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.CustomerProfileEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CustomerProfilePersistenceMapper
        extends PersistenceMapper<CustomerProfile, CustomerProfileEntity> {
}
