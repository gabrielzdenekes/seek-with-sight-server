package com.seek_with_sight.product.domain.model.category;

import java.util.List;
import java.util.UUID;

public record CategoryTreeItem(
        UUID id,
        String slug,
        String name,
        int sortOrder,
        String imageUrl,
        List<CategoryTreeItem> children
) {
}
