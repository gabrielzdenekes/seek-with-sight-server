package com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role;

import com.seek_with_sight.authorization.domain.model.role.Role;
import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.authorization.application.port.out.RoleRepositoryPort;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.entity.RoleEntity;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;

import java.util.List;

public class RolePersistenceAdapter
        extends BasePersistenceAdapter<Role, RoleEntity, RoleJpaRepository>
        implements RoleRepositoryPort {
    public RolePersistenceAdapter(
            RoleJpaRepository repository,
            PersistenceMapper<Role, RoleEntity> mapper) {
        super(repository, mapper, RoleEntity::new);
    }

    @Override
    public List<Role> findByNameIn(List<RoleName> roleNames) {
        return repository
                .findByNameIn(roleNames).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
