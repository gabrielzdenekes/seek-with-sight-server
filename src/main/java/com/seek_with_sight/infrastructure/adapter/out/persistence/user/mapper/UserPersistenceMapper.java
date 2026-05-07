package com.seek_with_sight.infrastructure.adapter.out.persistence.user.mapper;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserPersistenceMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);
}
