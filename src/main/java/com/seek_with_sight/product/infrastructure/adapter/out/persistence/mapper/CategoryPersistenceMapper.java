package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {JpaEntityFactory.class},
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface CategoryPersistenceMapper
        extends PersistenceMapper<Category, CategoryEntity> {
    @Override
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    Category toDomain(CategoryEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    CategoryEntity toEntity(Category domain, @Context CycleAvoidingMappingContext context);

    @Override
    void updateEntityFromDomain(
            Category domain,
            @MappingTarget CategoryEntity entity,
            @Context CycleAvoidingMappingContext context);
}
