package com.seek_with_sight.infrastructure.config.bean.user;

import com.seek_with_sight.application.service.user.CreateUserService;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.application.port.out.user.UserRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.UserPersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.mapper.UserPersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.user.repository.UserJpaRepository;
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
}
