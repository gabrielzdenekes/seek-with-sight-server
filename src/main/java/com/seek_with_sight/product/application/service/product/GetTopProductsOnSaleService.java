package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.GetTopProductsOnSaleUseCase;
import com.seek_with_sight.product.application.port.in.product.dto.ProductListItem;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import com.seek_with_sight.product.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class GetTopProductsOnSaleService implements GetTopProductsOnSaleUseCase {
    private final ProductRepositoryPort productsRepo;

    @Override
    public Page<ProductListItem> get(int productsCount) {
        var pageable = Pageable.ofSize(productsCount);

        return productsRepo.findTopDiscountedProducts(pageable);
    }
}
