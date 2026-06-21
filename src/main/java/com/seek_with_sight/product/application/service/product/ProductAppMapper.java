package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAppMapper {
    Product fromCreateCommand(CreateProductCommand command);
}
