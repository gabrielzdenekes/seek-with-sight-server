package com.seek_with_sight.product.application.service.product;

import com.seek_with_sight.product.application.port.in.product.GetLandingProductsUseCase;
import com.seek_with_sight.product.application.port.in.product.GetNewArrivalsUseCase;
import com.seek_with_sight.product.application.port.in.product.GetTopProductsOnSaleUseCase;
import com.seek_with_sight.product.application.port.in.product.dto.LandingProducts;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GetLandingProductsService implements GetLandingProductsUseCase {
    private final GetTopProductsOnSaleUseCase getTopProductsOnSaleUseCase;
    private final GetNewArrivalsUseCase getNewArrivalsUseCase;

    @Override
    public LandingProducts get(int productsCount) {
        var landingProducts = new LandingProducts(
                getTopProductsOnSaleUseCase.get(productsCount).stream().toList(),
                getNewArrivalsUseCase.get(productsCount).stream().toList()
        );

        return landingProducts;
    }
}
