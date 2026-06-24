package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductPersistenceMapper extends PersistenceMapper<Product, ProductEntity> {
    /**
     * Map WITHOUT mapping relationships.
     * */
    @Override
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "brand", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "seo", ignore = true)
    Product toDomain(ProductEntity entity);

    @Mapping(target = "category.parent", ignore = true)
    @Mapping(target = "category.children", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "brand.products", ignore = true)
    Product toDomainWithDetails(ProductEntity entity);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy =
            NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDomain(Product domain, @MappingTarget ProductEntity entity);
}
