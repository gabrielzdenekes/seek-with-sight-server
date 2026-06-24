package com.seek_with_sight.product.infrastructure.config.bean;

import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.in.tag.CreateTagUseCase;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.port.out.TagRepositoryPort;
import com.seek_with_sight.product.application.service.product.CreateProductService;
import com.seek_with_sight.product.application.service.product.GetProductByIdService;
import com.seek_with_sight.product.application.service.product.ProductAppMapper;
import com.seek_with_sight.product.application.service.tag.CreateTagService;
import com.seek_with_sight.product.application.service.tag.TagAppMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.BrandPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.CategoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.TagPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.BrandPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductTagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.TagJpaRepository;
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
    public TagRepositoryPort productTagRepositoryPort(
            TagJpaRepository repo,
            ProductTagPersistenceMapper mapper
    ) {
        return new TagPersistenceAdapter(repo, mapper);
    }

    @Bean
    public CreateTagUseCase createTagUseCase(
            TagAppMapper mapper,
            TagRepositoryPort repo
    ) {
        return new CreateTagService(mapper, repo);
    }
}
