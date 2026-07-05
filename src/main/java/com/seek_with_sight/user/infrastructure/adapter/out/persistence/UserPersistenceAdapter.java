package com.seek_with_sight.user.infrastructure.adapter.out.persistence;

import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.config.cache.CacheNames;
import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.entity.UserEntity;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.Optional;
import java.util.UUID;

public class UserPersistenceAdapter
        extends BasePersistenceAdapter<User, UserEntity, UserJpaRepository, UserPersistenceMapper>
        implements UserRepositoryPort {

    public UserPersistenceAdapter(UserJpaRepository repository, UserPersistenceMapper mapper) {
        super(repository, mapper, UserEntity::new);
    }

    @Override
    @Cacheable(
            cacheNames = CacheNames.USER,
            key = "#email.toLowerCase()",
            sync = true
    )
    @EntityGraph(attributePaths = {
            "roles",
            "roles.permissions"
    })
    public Optional<User> findByEmailIgnoreCase(String email) {
        return repository
                .findByEmailIgnoreCase(email)
                .map(e -> mapper.toDomain(e));
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository
                .findById(id)
                .map(e -> mapper.toDomain(e));
    }
}
