package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.CategoryEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface CategoryPersistenceMapper
        extends PersistenceMapper<Category, CategoryEntity> {
    @Override
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    Category toDomain(CategoryEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Category domain, @MappingTarget CategoryEntity entity);
}
