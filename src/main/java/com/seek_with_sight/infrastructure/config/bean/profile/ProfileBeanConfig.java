package com.seek_with_sight.infrastructure.config.bean.profile;

import com.seek_with_sight.application.port.in.profile.FindCustomerProfileByUserIdUseCase;
import com.seek_with_sight.application.port.in.profile.FindSellerProfileByUserIdUseCase;
import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.application.service.profile.CreateCustomerProfileService;
import com.seek_with_sight.application.service.profile.CreateSellerProfileService;
import com.seek_with_sight.application.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.application.service.profile.FindCustomerProfileByUserIdService;
import com.seek_with_sight.application.service.profile.FindSellerProfileByUserIdService;
import com.seek_with_sight.application.service.profile.mapper.CustomerProfileAppMapper;
import com.seek_with_sight.application.service.profile.mapper.SellerProfileAppMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.CustomerProfilePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.SellerProfilePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper.CustomerProfilePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.mapper.SellerProfilePersistenceMapper;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.CustomerProfileJpaRepository;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.repository.SellerProfileJpaRepository;
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
    public FindCustomerProfileByUserIdUseCase findCustomerProfileByUserIdUseCase(
            CustomerProfileRepositoryPort repo
    ) {
        return new FindCustomerProfileByUserIdService(repo);
    }

    @Bean
    public FindSellerProfileByUserIdUseCase findSellerProfileByUserIdUseCase(
            SellerProfileRepositoryPort repo
    ) {
        return new FindSellerProfileByUserIdService(repo);
    }
}
