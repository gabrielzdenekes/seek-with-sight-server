package com.seek_with_sight.product.infrastructure.config.bean;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.product.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.in.product.RemoveProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.UpdateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.tag.CreateTagUseCase;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.port.out.TagRepositoryPort;
import com.seek_with_sight.product.application.service.product.CreateProductService;
import com.seek_with_sight.product.application.service.product.CreateProductVariantService;
import com.seek_with_sight.product.application.service.product.GetProductByIdService;
import com.seek_with_sight.product.application.service.product.ProductAppMapper;
import com.seek_with_sight.product.application.service.product.RemoveProductVariantService;
import com.seek_with_sight.product.application.service.product.UpdateProductVariantService;
import com.seek_with_sight.product.application.service.tag.CreateTagService;
import com.seek_with_sight.product.application.service.tag.TagAppMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.BrandPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.CategoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.TagPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.AttributePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.BrandPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.seek_with_sight.media.infrastructure.out.persistence.mapper.ImagePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.TagPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.TagJpaRepository;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import jakarta.persistence.EntityManager;
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
            ProductPersistenceMapper mapper,
            EntityManager entityManager,
            ProductVariantPersistenceMapper variantMapper,
            TagPersistenceMapper tagsMapper,
            ImagePersistenceMapper imagesMapper,
            AttributePersistenceMapper attributesMapper) {

        return new ProductPersistenceAdapter(
                repo,
                mapper,
                entityManager,
                variantMapper,
                tagsMapper,
                imagesMapper,
                attributesMapper
        );
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepositoryPort productRepo,
            CategoryRepositoryPort categoryRepo,
            BrandRepositoryPort brandRepository,
            ProductAppMapper mapper,
            DomainEventPublisher publisher,
            ImageRepositoryPort imageRepo
    ) {
        return new CreateProductService(
                productRepo,
                categoryRepo,
                brandRepository,
                mapper,
                publisher,
                imageRepo);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(ProductRepositoryPort repo) {
        return new GetProductByIdService(repo);
    }

    @Bean
    public TagRepositoryPort productTagRepositoryPort(
            TagJpaRepository repo,
            TagPersistenceMapper mapper
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

    @Bean
    public CreateProductVariantUseCase createProductVariantUseCase(
            ProductRepositoryPort repo,
            ProductAppMapper mapper,
            ImageRepositoryPort imageRepo
    ) {
        return new CreateProductVariantService(repo, mapper, imageRepo);
    }

    @Bean
    public RemoveProductVariantUseCase removeProductVariantUseCase(
            ProductRepositoryPort repo
    ) {
        return new RemoveProductVariantService(repo);
    }

    @Bean
    public UpdateProductVariantUseCase updateProductVariantUseCase(
            ProductRepositoryPort repo,
            ProductAppMapper mapper,
            ImageRepositoryPort imagesRepo
    ) {
        return new UpdateProductVariantService(repo, mapper, imagesRepo);
    }
}
