package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.application.port.in.category.CategoryListItem;
import com.seek_with_sight.product.domain.model.Category;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort extends BaseRepositoryPort<Category> {
    Optional<Category> findById(UUID categoryId);

    List<CategoryListItem> getCategoryTree();
}
