package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.order.application.port.out.OrderRepositoryPort;
import com.seek_with_sight.product.application.port.in.product.GetLandingProductsUseCase;
import com.seek_with_sight.product.application.port.in.product.dto.LandingProducts;
import com.seek_with_sight.product.application.port.out.ProductRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class GetLandingProductsService implements GetLandingProductsUseCase {
    private final ProductRepositoryPort productsRepo;
    private final OrderRepositoryPort ordersRepo;

    @Override
    public LandingProducts get(int productsCount) {
        var pageable = Pageable.ofSize(productsCount);
        var landingProducts = new LandingProducts(
                productsRepo.findTopDiscountedProducts(pageable).toList(),
                productsRepo.findNewArrivals(pageable).toList(),
                ordersRepo.findBestSellingVariants(pageable).toList(),
                productsRepo.findBestReviewedProducts(pageable).toList()
        );

        return landingProducts;
    }
}
