package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.domain.model.category.CategorySearchItem;
import com.seek_with_sight.product.domain.model.category.CategoryTreeItem;
import com.seek_with_sight.product.application.port.in.category.GetCategoryTreeUseCase;
import com.seek_with_sight.product.application.port.in.category.SearchCategoriesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final GetCategoryTreeUseCase getCategoryTreeUseCase;
    private final SearchCategoriesUseCase searchCategoriesUseCase;

    @GetMapping
    public List<CategoryTreeItem> getCategoryTree() {
        var tree = getCategoryTreeUseCase.get();
        return tree;
    }

    @GetMapping("/search")
    public List<CategorySearchItem> search(
            @RequestParam(name = "q") String name
    ) {
        return searchCategoriesUseCase.search(name);
    }
}
