package com.seek_with_sight.product.infrastructure.config.bean;

import com.seek_with_sight.media.application.port.out.ImageRepositoryPort;
import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.product.application.port.in.brand.SearchBrandsUseCase;
import com.seek_with_sight.product.application.port.in.category.GetCategoryTreeUseCase;
import com.seek_with_sight.product.application.port.in.category.SearchCategoriesUseCase;
import com.seek_with_sight.product.application.port.in.image.AddProductImageUseCase;
import com.seek_with_sight.product.application.port.in.inventory.UpdateProductInventoryUseCase;
import com.seek_with_sight.product.application.port.in.product.GetLandingProductsUseCase;
import com.seek_with_sight.product.application.port.in.review.AddProductReviewUseCase;
import com.seek_with_sight.product.application.port.in.image.AddVariantImageUseCase;
import com.seek_with_sight.product.application.port.in.inventory.CreateProductInventoryUseCase;
import com.seek_with_sight.product.application.port.in.product.CreateProductUseCase;
import com.seek_with_sight.product.application.port.in.variant.CreateProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.in.review.GetProductReviewsUseCase;
import com.seek_with_sight.product.application.port.in.stock.ReleaseStockUseCase;
import com.seek_with_sight.product.application.port.in.variant.RemoveProductVariantUseCase;
import com.seek_with_sight.product.application.port.in.stock.ReserveStockUseCase;
import com.seek_with_sight.product.application.port.in.product.UpdateProductUseCase;
import com.seek_with_sight.product.application.port.in.variant.UpdateProductVariantUseCase;
import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.application.port.out.ProductReviewRepositoryPort;
import com.seek_with_sight.product.application.service.brand.SearchBrandsService;
import com.seek_with_sight.product.application.service.category.GetCategoryTreeService;
import com.seek_with_sight.product.application.service.category.SearchCategoriesService;
import com.seek_with_sight.product.application.service.image.AddProductImageService;
import com.seek_with_sight.product.application.service.inventory.UpdateProductInventoryService;
import com.seek_with_sight.product.application.service.product.GetLandingProductsService;
import com.seek_with_sight.product.application.service.review.AddProductReviewService;
import com.seek_with_sight.product.application.service.image.AddVariantImageService;
import com.seek_with_sight.product.application.service.inventory.CreateProductInventoryService;
import com.seek_with_sight.product.application.service.product.CreateProductService;
import com.seek_with_sight.product.application.service.variant.CreateProductVariantService;
import com.seek_with_sight.product.application.service.product.GetProductByIdService;
import com.seek_with_sight.product.application.service.review.GetProductReviewsService;
import com.seek_with_sight.product.application.service.product.ProductAppMapper;
import com.seek_with_sight.product.application.service.stock.ReleaseStockService;
import com.seek_with_sight.product.application.service.variant.RemoveProductVariantService;
import com.seek_with_sight.product.application.service.stock.ReserveStockService;
import com.seek_with_sight.product.application.service.product.UpdateProductService;
import com.seek_with_sight.product.application.service.variant.SalePriceService;
import com.seek_with_sight.product.application.service.variant.UpdateProductVariantService;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.BrandPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.CategoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductInventoryPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.ProductReviewPersistenceAdapter;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.BrandPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductImagePersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductInventoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductReviewPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductVariantPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductInventoryJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductReviewJpaRepository;
import com.seek_with_sight.shared.application.port.out.event.DomainEventPublisher;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
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
            CategoryJpaRepository categoryRepo,
            BrandJpaRepository brandRepo,
            ProductImagePersistenceMapper imageMapper
    ) {

        return new ProductPersistenceAdapter(
                repo,
                mapper,
                entityManager,
                variantMapper,
                categoryRepo,
                brandRepo,
                imageMapper
        );
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductRepositoryPort productRepo,
            CategoryRepositoryPort categoryRepo,
            BrandRepositoryPort brandRepository,
            ProductAppMapper mapper,
            DomainEventPublisher publisher,
            ImageRepositoryPort imageRepo,
            ProductInventoryRepositoryPort inventoryRepo,
            CreateProductInventoryUseCase createProductInventoryUseCase
    ) {
        return new CreateProductService(
                productRepo,
                categoryRepo,
                brandRepository,
                mapper,
                publisher,
                imageRepo,
                inventoryRepo,
                createProductInventoryUseCase);
    }

    @Bean
    public GetProductByIdUseCase getProductByIdUseCase(ProductRepositoryPort repo) {
        return new GetProductByIdService(repo);
    }

    @Bean
    public CreateProductVariantUseCase createProductVariantUseCase(
            ProductRepositoryPort repo,
            ProductAppMapper mapper,
            ImageRepositoryPort imageRepo,
            CreateProductInventoryUseCase createProductInventoryUseCase
    ) {
        return new CreateProductVariantService(
                repo,
                mapper,
                imageRepo,
                createProductInventoryUseCase
        );
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
            SalePriceService salePriceService
    ) {
        return new UpdateProductVariantService(repo, mapper, salePriceService);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
            ProductRepositoryPort productsRepo,
            CategoryRepositoryPort categoriesRepo,
            BrandRepositoryPort brandsRepo,
            ProductAppMapper productAppMapper,
            ImageRepositoryPort imagesRepo,
            DomainEventPublisher domainEventPublisher
    ) {
        return new UpdateProductService(
                productsRepo,
                categoriesRepo,
                brandsRepo,
                productAppMapper,
                imagesRepo,
                domainEventPublisher
        );
    }

    @Bean
    public AddProductImageUseCase addProductImageUseCase(
            ProductRepositoryPort productsRepo
    ) {
        return new AddProductImageService(productsRepo);
    }

    @Bean
    public AddVariantImageUseCase addVariantImageUseCase(
            ProductRepositoryPort productsRepo
    ) {
        return new AddVariantImageService(productsRepo);
    }

    @Bean
    public ProductInventoryRepositoryPort productInventoryRepositoryPort(
            ProductInventoryJpaRepository repo,
            ProductInventoryPersistenceMapper mapper
    ) {
        return new ProductInventoryPersistenceAdapter(
                repo,
                mapper
        );
    }

    @Bean
    public ReserveStockUseCase reserveStockUseCase(ProductInventoryRepositoryPort repo) {
        return new ReserveStockService(repo);
    }

    @Bean
    public ReleaseStockUseCase releaseStockUseCase(ProductInventoryRepositoryPort repo) {
        return new ReleaseStockService(repo);
    }

    @Bean
    public ProductReviewRepositoryPort productReviewRepositoryPort(
            ProductReviewJpaRepository repository,
            ProductReviewPersistenceMapper mapper
    ) {
        return new ProductReviewPersistenceAdapter(repository, mapper);
    }

    @Bean
    public AddProductReviewUseCase addProductReviewUseCase(
            ProductReviewRepositoryPort reviewsRepo,
            ProductRepositoryPort productsRepo,
            CurrentUserPort currentUserPort,
            ProductAppMapper mapper
    ) {
        return new AddProductReviewService(
                reviewsRepo,
                productsRepo,
                currentUserPort,
                mapper
        );
    }

    @Bean
    public GetProductReviewsUseCase getProductReviewsUseCase(ProductReviewRepositoryPort repo) {
        return new GetProductReviewsService(repo);
    }

    @Bean
    public CreateProductInventoryUseCase createProductInventoryUseCase(
            ProductInventoryRepositoryPort repo
    ) {
        return new CreateProductInventoryService(repo);
    }

    @Bean
    public UpdateProductInventoryUseCase updateProductInventoryUseCase(ProductInventoryRepositoryPort repo) {
        return new UpdateProductInventoryService(repo);
    }

    @Bean
    public GetCategoryTreeUseCase getCategoriesUseCase(CategoryRepositoryPort repo) {
        return new GetCategoryTreeService(repo);
    }

    @Bean
    public GetLandingProductsUseCase getLandingProductsUseCase(
            ProductRepositoryPort productsRepo,
            OrderRepositoryPort ordersRepo
    ) {
        return new GetLandingProductsService(productsRepo, ordersRepo);
    }

    @Bean
    public SearchCategoriesUseCase searchCategoriesUseCase(CategoryRepositoryPort categoryRepository) {
        return new SearchCategoriesService(categoryRepository);
    }

    @Bean
    public SearchBrandsUseCase searchBrandsUseCase(BrandRepositoryPort brandRepo) {
        return new SearchBrandsService(brandRepo);
    }
}
