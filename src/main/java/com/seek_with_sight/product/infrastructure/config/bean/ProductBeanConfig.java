package com.seek_with_sight.product.infrastructure.config.bean;

import com.seek_with_sight.product.application.port.in.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductTagRepositoryPort;
import com.seek_with_sight.product.application.service.CreateProductService;
import com.seek_with_sight.product.application.service.GetProductByIdService;
import com.seek_with_sight.product.application.service.mapper.ProductAppMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.BrandPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.CategoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductTagPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.BrandPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductTagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductTagJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductBeanConfig {
    @Bean
    public BrandRepositoryPort brandRepositoryPort(
            BrandJpaRepository repo,
            BrandPersistenceMapper mapper) {
        return new BrandPersistenceAdapter(repo, mapper);
    }

    @Bean
    public CategoryRepositoryPort categoryRepositoryPort(
            CategoryJpaRepository repo,
            CategoryPersistenceMapper mapper) {
        return new CategoryPersistenceAdapter(repo, mapper);
    }

    @Bean
    public ProductRepositoryPort productRepositoryPort(
            ProductJpaRepository repo,
            ProductPersistenceMapper mapper) {
        return new ProductPersistenceAdapter(repo, mapper);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepositoryPort productRepo,
            CategoryRepositoryPort categoryRepo,
            BrandRepositoryPort brandRepository,
            ProductAppMapper mapper
    ) {
        return new CreateProductService(productRepo, categoryRepo, brandRepository, mapper);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(ProductRepositoryPort repo) {
        return new GetProductByIdService(repo);
    }

    @Bean
    public ProductTagRepositoryPort productTagRepositoryPort(
            ProductTagJpaRepository repo,
            ProductTagPersistenceMapper mapper
    ) {
        return new ProductTagPersistenceAdapter(repo, mapper);
    }
}
