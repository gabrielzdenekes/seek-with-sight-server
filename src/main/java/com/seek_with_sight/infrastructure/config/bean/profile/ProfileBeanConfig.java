package com.seek_with_sight.infrastructure.config.bean.profile;

import com.seek_with_sight.application.service.profile.CreateCustomerProfileService;
import com.seek_with_sight.application.service.profile.CreateSellerProfileService;
import com.seek_with_sight.application.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.CustomerProfilePersistenceAdapter;
import com.seek_with_sight.infrastructure.adapter.out.persistence.profile.SellerProfilePersistenceAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProfileBeanConfig {
    @Bean
    public CustomerProfileRepositoryPort customerProfileRepositoryPort() {
        return new CustomerProfilePersistenceAdapter();
    }

    @Bean
    public SellerProfileRepositoryPort sellerProfileRepositoryPort() {
        return new SellerProfilePersistenceAdapter();
    }

    @Bean
    public CreateSellerProfileUseCase createSellerProfileUseCase() {
        return new CreateSellerProfileService();
    }

    @Bean
    public CreateCustomerProfileUseCase createCustomerProfileUseCase() {
        return  new CreateCustomerProfileService();
    }
}
