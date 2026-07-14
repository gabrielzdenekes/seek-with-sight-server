package com.seek_with_sight.search.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.search.infrastructure.adapter.in.rest.dto.ProductSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductSearchRestMapper {
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "brandName", source = "brand.name")
    ProductSearchResponse toResponse(Product product);
}
