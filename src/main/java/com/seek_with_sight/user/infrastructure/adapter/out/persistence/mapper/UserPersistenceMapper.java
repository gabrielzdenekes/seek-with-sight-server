package com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
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
