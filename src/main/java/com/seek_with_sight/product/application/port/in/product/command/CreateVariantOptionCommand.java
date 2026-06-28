package com.seek_with_sight.product.application.port.in.product.command;

public record CreateVariantOptionCommand(
        String name,
        String value,
        Integer sortOrder
) {
}
