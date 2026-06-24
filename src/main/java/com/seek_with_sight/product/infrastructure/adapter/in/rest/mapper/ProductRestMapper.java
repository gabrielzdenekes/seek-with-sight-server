package com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    CreateProductCommand toCreateProductCommand(ProductRequest request);

    ProductResponse toResponse(Product product);

    ProductResponseWithDetails toResponseWithDetails(Product product);
}
