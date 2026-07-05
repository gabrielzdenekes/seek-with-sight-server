package com.seek_with_sight.product.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductVariantCommand;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductCommand;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.domain.model.ProductVariant;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.UpdateProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductRestMapper {
    CreateProductCommand toCreateProductCommand(ProductRequest request);

    UpdateProductCommand toUpdateProductCommand(UpdateProductRequest request);

    ProductResponse toResponse(Product product);

    ProductResponseWithDetails toResponseWithDetails(Product product);

    CreateProductVariantCommand toCreateProductVariantCommand(ProductVariantRequest request);

    ProductVariantResponse toVariantResponse(ProductVariant variant);

    UpdateProductVariantCommand toUpdateProductVariantCommand(ProductVariantRequest request);
}
