package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.CategoryRepositoryPort;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CategoryPersistenceAdapter implements CategoryRepositoryPort {
    private final CategoryJpaRepository repo;
}
