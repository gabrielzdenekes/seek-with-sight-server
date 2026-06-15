package com.seek_with_sight.product.application.port.in.create.command;

public record CreateProductAttributeCommand(
        String name,

        String value,

        Boolean isFilterable,

        Integer sortOrder
) {
}
