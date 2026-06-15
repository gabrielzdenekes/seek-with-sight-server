package com.seek_with_sight.product.application.service.mapper;

import com.seek_with_sight.product.application.port.in.create.command.CreateProductCommand;
import com.seek_with_sight.product.domain.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductAppMapper {
    Product fromCreateCommand(CreateProductCommand command);
}
