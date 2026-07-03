package com.seek_with_sight.product.infrastructure.adapter.out.persistence.mapper;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.entity.ProductEntity;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.CycleAvoidingMappingContext;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.JpaEntityFactory;
import com.seek_with_sight.shared.infrastructure.adapter.out.persistence.PersistenceMapper;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(
        componentModel = "spring",
        uses = {
                JpaEntityFactory.class,
                ProductVariantPersistenceMapper.class,
                CategoryPersistenceMapper.class
        },
        collectionMappingStrategy = CollectionMappingStrategy.ACCESSOR_ONLY
)
public interface ProductPersistenceMapper extends PersistenceMapper<Product, ProductEntity> {
    @Override
    @Named("basicToDomain")
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "images", ignore = true)
    Product toDomain(ProductEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "images", ignore = true)
    ProductEntity toEntity(Product domain,  @Context CycleAvoidingMappingContext context);

    Product toDomainWithDetails(ProductEntity entity, @Context CycleAvoidingMappingContext context);

    @Override
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "variants", ignore = true)
    @Mapping(target = "attributes", ignore = true)
    @Mapping(target = "images", ignore = true)
    void updateEntityFromDomain(
            Product domain,
            @MappingTarget ProductEntity entity,
            @Context CycleAvoidingMappingContext context);
}
