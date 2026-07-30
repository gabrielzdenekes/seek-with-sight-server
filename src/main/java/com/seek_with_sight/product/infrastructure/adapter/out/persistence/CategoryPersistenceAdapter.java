package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.in.category.CategoryListItem;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper.CategoryPersistenceMapper;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.BasePersistenceAdapter;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CategoryPersistenceAdapter
        extends BasePersistenceAdapter<Category, CategoryEntity, CategoryJpaRepository, CategoryPersistenceMapper>
        implements CategoryRepositoryPort {

    public CategoryPersistenceAdapter(
            CategoryJpaRepository repo,
            CategoryPersistenceMapper mapper) {
        super(repo, mapper, CategoryEntity::new);
    }

    @Override
    public Optional<Category> findById(UUID categoryId) {
        return repository.findById(categoryId).map((e) -> mapper.toDomain(e, new CycleAvoidingMappingContext()));
    }

    @Override
    public List<CategoryListItem> getCategoryTree() {
        return repository.findAllByParentIsNullOrderBySortOrderAsc();
    }
}
