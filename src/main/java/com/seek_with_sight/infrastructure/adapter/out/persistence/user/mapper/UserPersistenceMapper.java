package com.seek_with_sight.infrastructure.adapter.out.persistence.user.mapper;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.out.persistence.shared.PersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper extends PersistenceMapper<User, UserEntity> {
    @Override
    User toDomain(UserEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);
}
