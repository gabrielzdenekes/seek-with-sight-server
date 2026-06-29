package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.application.port.in.product.command.CreateProductVariantCommand;
import com.seek_with_sight.product.application.port.in.product.command.UpdateProductVariantCommand;
import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.product.domain.model.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductAppMapper {
    Product fromCreateCommand(CreateProductCommand command);

    ProductVariant fromCreateCommand(CreateProductVariantCommand command);

    void updateVariant(
            UpdateProductVariantCommand command,
            @MappingTarget ProductVariant variant
    );
}
