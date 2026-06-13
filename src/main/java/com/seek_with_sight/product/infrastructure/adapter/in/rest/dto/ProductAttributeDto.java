package com.seek_with_sight.product.infrastructure.adapter.in.rest.dto;

public record ProductAttributeDto(
        String name,
        String value,
        Boolean isFilterable,
        Integer sortOrder
) {
}
