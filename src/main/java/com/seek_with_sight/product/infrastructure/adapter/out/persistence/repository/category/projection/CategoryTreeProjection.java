package com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection;

import java.util.List;
import java.util.UUID;

public interface CategoryTreeProjection {
    UUID getId();

    String getSlug();

    String getName();

    List<CategoryTreeProjection> getChildren();

    int getSortOrder();

    String getImageUrl();
}
