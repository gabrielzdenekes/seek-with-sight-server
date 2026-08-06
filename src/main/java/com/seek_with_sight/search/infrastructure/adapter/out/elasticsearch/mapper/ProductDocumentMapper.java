package com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.mapper;

import com.seek_with_sight.product.domain.model.product.Product;
import com.seek_with_sight.search.infrastructure.adapter.out.elasticsearch.documents.ProductDocument;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductDocumentMapper {

    @Mapping(target = "id", source = "id", qualifiedByName = "uuidToString")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "brandName", source = "brand.name")
    ProductDocument toDocument(Product product);

    @Mapping(target = "category.name", source = "categoryName")
    @Mapping(target = "brand.name", source = "brandName")
    Product toDomain(ProductDocument document);

    @Named("uuidToString")
    default String uuidToString(UUID id) {
        return id != null ? id.toString() : null;
    }
}
