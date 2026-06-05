package com.seek_with_sight.infrastructure.adapter.out.persistence.auth.mapper;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper {
    RefreshToken fromEntity(RefreshTokenEntity entity);

    void updateEntityFromDomain(
            RefreshToken domain,
            @MappingTarget RefreshTokenEntity entity
    );
}
