package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.domain.model.Tag;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.TagEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.ProductPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.ProductJpaRepository;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.TagJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.config.cache.CacheNames;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProductPersistenceAdapter
        extends BasePersistenceAdapter<Product, ProductEntity, ProductJpaRepository>
        implements ProductRepositoryPort {
    private final ProductPersistenceMapper mapper;
    private final TagJpaRepository tagsRepo;

    public ProductPersistenceAdapter(ProductJpaRepository repository, ProductPersistenceMapper mapper, TagJpaRepository tagsRepo) {
        super(repository, mapper, ProductEntity::new);
        this.mapper = mapper;
        this.tagsRepo = tagsRepo;
    }

    @Override
    @Cacheable(
            cacheNames = CacheNames.PRODUCTS,
            key = "#id"
    )
    public Optional<Product> findById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::toDomainWithDetails);
    }

    @Override
    protected void syncComplexProperties(Product domain, ProductEntity entity) {
        super.syncComplexProperties(domain, entity);
    }

    private void syncTags(Product domain, ProductEntity entity) {
        var currentEntityTagsMap = entity
                .getTags()
                .stream()
                .collect(Collectors.toMap(TagEntity::getId, Function.identity()));

        var domainTagsIds = domain
                .getTags()
                .stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());

        // Remove entity tags that don't match with the domain tags
        entity.getTags().removeIf(t -> !domainTagsIds.contains(t.getId()));

        // Add new entity tags, that exist in domain tags
        for (var tagId : domainTagsIds) {
            if (!currentEntityTagsMap.containsKey(tagId)) {
                var tagEntity = tagsRepo.getReferenceById(tagId);
                entity.getTags().add(tagEntity);
            }
        }
    }
}
