package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.domain.model.product.Product;

import java.util.UUID;

public interface GetProductByIdUseCase {
    Product getById(UUID id);
}
