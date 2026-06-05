package com.seek_with_sight.infrastructure.adapter.out.persistence.role.mapper;

import com.seek_with_sight.domain.model.role.Role;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface RolePersistenceMapper extends PersistenceMapper<Role, RoleEntity> {
    @Override
    Role toDomain(RoleEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Role domain, @MappingTarget RoleEntity entity);
}
