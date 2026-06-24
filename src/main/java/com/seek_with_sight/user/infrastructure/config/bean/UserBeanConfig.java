package com.seek_with_sight.user.infrastructure.config.bean;

import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import com.seek_with_sight.user.application.service.CreateUserService;
import com.seek_with_sight.user.application.port.in.CreateUserUseCase;
import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.UserPersistenceAdapter;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.mapper.UserPersistenceMapper;
import com.seek_with_sight.user.infrastructure.adapter.out.persistence.repository.UserJpaRepository;
import com.seek_with_sight.user.infrastructure.adapter.out.security.SecurityCurrentUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserBeanConfig {
    @Bean
    public UserRepositoryPort userRepository(UserJpaRepository repository, UserPersistenceMapper mapper) {
        return new UserPersistenceAdapter(repository, mapper);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(CreateUserService createUserService) {
        return createUserService;
    }

    @Bean
    public CurrentUserPort currentUserPort(UserRepositoryPort userRepositoryPort) {
        return new SecurityCurrentUserAdapter(userRepositoryPort);
    }
}
