package com.seek_with_sight.product.application.service.category;

import com.seek_with_sight.product.application.port.in.category.CategoryListItem;
import com.seek_with_sight.product.application.port.in.category.GetCategoriesUseCase;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetCategoriesService implements GetCategoriesUseCase {
    private final CategoryRepositoryPort repo;

    @Override
    public List<CategoryListItem> get() {
        return repo.findAll();
    }
}
