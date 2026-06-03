package com.seek_with_sight.infrastructure.config.bean.role;

import com.seek_with_sight.application.port.out.role.RoleRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.RolePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.mapper.RolePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.role.repository.RoleJpaRepository;
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
