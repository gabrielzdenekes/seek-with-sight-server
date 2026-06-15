package com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.FullProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    CreateProductCommand toCreateProductCommand(ProductRequest request);

    FullProductResponse toResponse(Product product);
}
