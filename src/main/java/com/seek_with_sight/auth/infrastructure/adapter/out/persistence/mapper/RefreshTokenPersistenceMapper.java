package com.seek_with_sight.auth.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.auth.domain.model.RefreshToken;
import com.seek_with_sight.auth.infrastructure.adapter.out.persistence.entity.RefreshTokenEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RefreshTokenPersistenceMapper extends PersistenceMapper<RefreshToken, RefreshTokenEntity> {
    @Override
    RefreshToken toDomain(RefreshTokenEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(RefreshToken domain, @MappingTarget RefreshTokenEntity entity);
}
