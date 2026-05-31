package com.seek_with_sight.infrastructure.config.bean;

import com.seek_with_sight.domain.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.domain.port.out.profile.SellerProfileRepositoryPort;
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
}
