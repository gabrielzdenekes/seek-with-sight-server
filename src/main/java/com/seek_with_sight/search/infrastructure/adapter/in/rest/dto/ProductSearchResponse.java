package com.seek_with_sight.search.infrastructure.adapter.in.rest.dto;

import java.util.UUID;

public record ProductSearchResponse(
        UUID id,
        String name,
        String categoryName,
        String brandName
) {
}
