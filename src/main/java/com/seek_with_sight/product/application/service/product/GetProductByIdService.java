package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class GetProductByIdService implements GetProductByIdUseCase {
    private final ProductRepositoryPort productRepo;

    @Override
    @Transactional(readOnly = true)
    public Product getById(UUID id) {
        return productRepo
                .findById(id)
                .orElseThrow();
    }
}
