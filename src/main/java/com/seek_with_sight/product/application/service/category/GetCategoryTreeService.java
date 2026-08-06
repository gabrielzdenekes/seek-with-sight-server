package com.seek_with_sight.product.application.service.category;

import com.seek_with_sight.product.domain.model.category.CategoryTreeItem;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.category.projection.CategoryTreeProjection;
import com.seek_with_sight.product.application.port.in.category.GetCategoryTreeUseCase;
import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@RequiredArgsConstructor
public class GetCategoryTreeService implements GetCategoryTreeUseCase {
    private final CategoryRepositoryPort repo;

    @Override
    @Cacheable(value = "categoriesTree")
    public List<CategoryTreeItem> get() {
        return repo.getCategoryTree();
    }
}
