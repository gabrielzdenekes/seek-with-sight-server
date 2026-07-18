package com.seek_with_sight.product.application.service.image;

import com.seek_with_sight.media.domain.model.Image;
import com.seek_with_sight.product.application.port.in.image.AddProductImageUseCase;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.exception.ProductNotFoundException;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
public class AddProductImageService implements AddProductImageUseCase {
    private final ProductRepositoryPort productsRepo;

    @Override
    @Transactional
    public Product add(UUID productId, Image image) {
        var product = productsRepo.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(new Object[] { productId }));

        product.addImage(image);

        return productsRepo.save(product);
    }
}
