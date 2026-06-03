package com.seek_with_sight.infrastructure.adapter.out.persistence.role.mapper;

import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper {
    Role toDomain(RoleEntity roleEntity);
}
