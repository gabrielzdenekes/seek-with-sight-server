package com.seek_with_sight.product.application.port.in.category;

import java.util.List;
import java.util.UUID;

public interface CategoryListItem {
    UUID getId();

    String getSlug();

    String getName();

    List<CategoryListItem> getChildren();

    int getSortOrder();

    String getImageUrl();
}
