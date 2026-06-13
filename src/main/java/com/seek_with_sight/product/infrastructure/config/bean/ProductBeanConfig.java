package com.seek_with_sight.product.infrastructure.config.bean;

import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.BrandPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.CategoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfig {
    @Bean
    public BrandRepositoryPort brandRepositoryPort(BrandJpaRepository repo) {
        return new BrandPersistenceAdapter(repo);
    }

    @Bean
    public CategoryRepositoryPort categoryRepositoryPort(CategoryJpaRepository repo) {
        return new CategoryPersistenceAdapter(repo);
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(ProductJpaRepository repo) {
        return new ProductPersistenceAdapter(repo);
    }
}
