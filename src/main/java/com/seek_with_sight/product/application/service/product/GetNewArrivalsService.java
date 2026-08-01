package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.GetNewArrivalsUseCase;
import com.seek_with_sight.product.application.port.in.product.dto.ProductListItem;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class GetNewArrivalsService implements GetNewArrivalsUseCase {
    private final ProductRepositoryPort productsRepo;

    @Override
    public Page<ProductListItem> get(int productsCount) {
        var pageable = Pageable.ofSize(productsCount);

        return productsRepo.findNewArrivals(pageable);
    }
}
