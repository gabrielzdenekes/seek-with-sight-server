package com.seek_with_sight.product.application.port.in.category;

import com.seek_with_sight.product.domain.model.category.CategorySearchItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategorySearchProjection;

import java.util.List;

public interface SearchCategoriesUseCase {
    List<CategorySearchItem> search(String name);
}
