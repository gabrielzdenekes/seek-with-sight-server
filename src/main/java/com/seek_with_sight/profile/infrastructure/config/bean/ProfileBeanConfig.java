package com.seek_with_sight.profile.infrastructure.config.bean;

import com.seek_with_sight.profile.application.port.in.FindCurrentUserCustomerProfile;
import com.seek_with_sight.profile.application.port.in.FindCurrentUserSellerProfileUseCase;
import com.seek_with_sight.user.application.port.in.CreateUserUseCase;
import com.seek_with_sight.profile.application.service.CreateCustomerProfileService;
import com.seek_with_sight.profile.application.service.CreateSellerProfileService;
import com.seek_with_sight.profile.application.port.in.CreateCustomerProfileUseCase;
import com.seek_with_sight.profile.application.port.in.CreateSellerProfileUseCase;
import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.application.port.out.SellerProfileRepositoryPort;
import com.seek_with_sight.profile.application.service.FindCurrentUserCustomerProfileService;
import com.seek_with_sight.profile.application.service.FindCurrentUserSellerProfileService;
import com.seek_with_sight.profile.application.service.mapper.CustomerProfileAppMapper;
import com.seek_with_sight.profile.application.service.mapper.SellerProfileAppMapper;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.CustomerProfilePersistenceAdapter;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.SellerProfilePersistenceAdapter;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper.CustomerProfilePersistenceMapper;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.mapper.SellerProfilePersistenceMapper;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.CustomerProfileJpaRepository;
import com.seek_with_sight.profile.infrastructure.adapter.out.persistence.repository.SellerProfileJpaRepository;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileBeanConfig {
    @Bean
    public CustomerProfileRepositoryPort customerProfileRepositoryPort(
            CustomerProfileJpaRepository repo,
            CustomerProfilePersistenceMapper mapper
    ) {
        return new CustomerProfilePersistenceAdapter(repo, mapper);
    }

    @Bean
    public SellerProfileRepositoryPort sellerProfileRepositoryPort(
            SellerProfileJpaRepository repo,
            SellerProfilePersistenceMapper mapper
    ) {
        return new SellerProfilePersistenceAdapter(
                repo,
                mapper
        );
    }

    @Bean
    public CreateSellerProfileUseCase createSellerProfileUseCase(
            CreateUserUseCase createUserUseCase,
            SellerProfileAppMapper mapper,
            SellerProfileRepositoryPort repo
    ) {
        return new CreateSellerProfileService(createUserUseCase, mapper, repo);
    }

    @Bean
    public CreateCustomerProfileUseCase createCustomerProfileUseCase(
            CreateUserUseCase createUserUseCase,
            CustomerProfileAppMapper mapper,
            CustomerProfileRepositoryPort repo
    ) {
        return  new CreateCustomerProfileService(createUserUseCase, mapper, repo);
    }

    @Bean
    public FindCurrentUserCustomerProfile findCustomerProfileByUserIdUseCase(
            CustomerProfileRepositoryPort repo,
            CurrentUserPort currentUserPort
    ) {
        return new FindCurrentUserCustomerProfileService(currentUserPort, repo);
    }

    @Bean
    public FindCurrentUserSellerProfileUseCase findSellerProfileByUserIdUseCase(
            SellerProfileRepositoryPort repo,
            CurrentUserPort currentUserPort
    ) {
        return new FindCurrentUserSellerProfileService(repo, currentUserPort);
    }
}
