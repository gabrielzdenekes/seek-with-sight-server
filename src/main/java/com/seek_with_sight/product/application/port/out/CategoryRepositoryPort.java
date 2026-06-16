package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.domain.model.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepositoryPort {
    Optional<Category> findById(UUID categoryId);
}
