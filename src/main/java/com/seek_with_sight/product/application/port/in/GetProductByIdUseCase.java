package com.seek_with_sight.product.application.port.in;

import com.seek_with_sight.product.domain.model.Product;

import java.util.UUID;

public interface GetProductByIdUseCase {
    Product getById(UUID id);
}
