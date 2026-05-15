package com.seek_with_sight.infrastructure.adapter.out.persistence.auth.mapper;

import com.seek_with_sight.domain.model.auth.RefreshToken;
import com.seek_with_sight.infrastructure.adapter.out.persistence.auth.entity.RefreshTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper {
    RefreshToken fromEntity(RefreshTokenEntity entity);
}
