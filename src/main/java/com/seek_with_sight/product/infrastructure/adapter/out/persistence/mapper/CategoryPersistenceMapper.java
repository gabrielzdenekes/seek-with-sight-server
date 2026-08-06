package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.category.Category;
import com.seek_with_sight.product.domain.model.category.CategorySearchItem;
import com.seek_with_sight.product.domain.model.category.CategoryTreeItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategorySearchProjection;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategoryTreeProjection;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {JpaEntityFactory.class},
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CategoryPersistenceMapper
        extends PersistenceMapper<Category, CategoryEntity> {
    @Override
    @Mapping(target = "children", ignore = true)
    Category toDomain(CategoryEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    CategoryEntity toEntity(Category domain, @Context CycleAvoidingMappingContext context);

    CategoryTreeItem toTreeItem(CategoryTreeProjection projection, @Context CycleAvoidingMappingContext context);

    CategorySearchItem toSearchItem(CategorySearchProjection projection, @Context CycleAvoidingMappingContext context);
}
