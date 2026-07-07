package com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        uses = { JpaEntityFactory.class },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface UserPersistenceMapper extends PersistenceMapper<User, UserEntity> {
}
