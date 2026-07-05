package com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.authentication.domain.model.RefreshToken;
import com.seek_with_sight.authentication.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface RefreshTokenPersistenceMapper extends PersistenceMapper<RefreshToken, RefreshTokenEntity> {
    @Override
    RefreshToken toDomain(RefreshTokenEntity entity);

    @Override
    RefreshTokenEntity toEntity(RefreshToken domain);
}
