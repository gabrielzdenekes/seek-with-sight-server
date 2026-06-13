package com.seek_with_sight.product.infrastructure.adapter.out.persistence;

import com.seek_with_sight.product.application.port.out.BrandRepositoryPort;
import com.seek_with_sight.product.infrastructure.adapter.out.persistence.repository.BrandJpaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandPersistenceAdapter implements BrandRepositoryPort {
    private final BrandJpaRepository repo;
}
