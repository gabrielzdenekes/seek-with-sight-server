package com.seek_with_sight.product.application.port.out;

import com.seek_with_sight.product.domain.model.Product;
import com.seek_with_sight.shared.application.port.out.BaseRepositoryPort;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepositoryPort extends BaseRepositoryPort<Product> {
    Optional<Product> getById(UUID id);
}
