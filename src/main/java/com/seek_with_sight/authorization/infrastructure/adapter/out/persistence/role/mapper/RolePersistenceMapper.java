package com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.mapper;

import com.seek_with_sight.authorization.domain.model.role.Role;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface RolePersistenceMapper extends PersistenceMapper<Role, RoleEntity> {
    @Override
    Role toDomain(RoleEntity entity);

    @Override
    RoleEntity toEntity(Role domain);
}
