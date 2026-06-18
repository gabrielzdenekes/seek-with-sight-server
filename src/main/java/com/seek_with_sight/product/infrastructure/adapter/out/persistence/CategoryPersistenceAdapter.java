package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;

import java.util.Optional;
import java.util.UUID;

public class CategoryPersistenceAdapter
        extends BasePersistenceAdapter<Category, CategoryEntity, CategoryJpaRepository>
        implements CategoryRepositoryPort {

    public CategoryPersistenceAdapter(
            CategoryJpaRepository repo,
            PersistenceMapper<Category, CategoryEntity> mapper) {
        super(repo, mapper, CategoryEntity::new);
    }

    @Override
    public Optional<Category> findById(UUID categoryId) {
        return repository.findById(categoryId).map(mapper::toDomain);
    }
}
