package com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.entity.SellerProfileEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface SellerProfilePersistenceMapper extends PersistenceMapper<SellerProfile, SellerProfileEntity> {
    @Override
    SellerProfile toDomain(SellerProfileEntity entity);

    @Override
    SellerProfileEntity toEntity(SellerProfile domain);
}
