package com.seek_with_sight.product.infrastructure.adapter.in.rest;

import com.seek_with_sight.product.application.port.in.category.CategoryListItem;
import com.seek_with_sight.product.application.port.in.category.GetCategoriesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoriesController {
    private final GetCategoriesUseCase getCategoriesUseCase;

    @GetMapping
    public List<CategoryListItem> getCategoryTree() {
        var tree = getCategoriesUseCase.get();
        return tree;
    }
}
