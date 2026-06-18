package com.seek_with_sight.product.application.service;

import com.seek_with_sight.product.application.port.in.GetProductByIdUseCase;
import com.seek_with_sight.product.domain.model.Product;

import java.util.UUID;

public class GetProductByIdService implements GetProductByIdUseCase {
    @Override
    public Product getById(UUID id) {
        return null;
    }
}
