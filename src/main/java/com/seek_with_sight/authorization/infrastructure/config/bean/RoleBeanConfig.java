package com.seek_with_sight.authorization.infrastructure.config.bean;

import com.seek_with_sight.authorization.application.port.out.RoleRepositoryPort;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.RolePersistenceAdapter;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.mapper.RolePersistenceMapper;
import com.seek_with_sight.authorization.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoleBeanConfig {
    @Bean
    public RoleRepositoryPort roleRepositoryPort(
            RoleJpaRepository roleRepository,
            RolePersistenceMapper roleMapper
    ) {
        return new RolePersistenceAdapter(
                roleRepository,
                roleMapper
        );
    }
}
