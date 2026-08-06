package com.seek_with_sight.product.application.port.in.category;

import com.seek_with_sight.product.domain.model.category.CategoryTreeItem;

import java.util.List;

public interface GetCategoryTreeUseCase {
    List<CategoryTreeItem> get();
}
