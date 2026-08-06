package com.seek_with_sight.product.application.service.category;

import com.seek_with_sight.product.domain.model.category.CategorySearchItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategorySearchProjection;
import com.seek_with_sight.product.application.port.in.category.SearchCategoriesUseCase;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SearchCategoriesService implements SearchCategoriesUseCase {
    private final CategoryRepositoryPort categoryRepository;

    @Override
    public List<CategorySearchItem> search(String name) {
        return categoryRepository.searchByName(name);
    }
}
