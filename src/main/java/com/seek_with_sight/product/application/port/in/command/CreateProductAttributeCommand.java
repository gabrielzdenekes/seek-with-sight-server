package com.seek_with_sight.product.application.port.in.command;

public record CreateProductAttributeCommand(
        String name,

        String value,

        Boolean isFilterable,

        Integer sortOrder
) {
}
