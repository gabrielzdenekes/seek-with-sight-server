package com.seek_with_sight.product.application.port.in.product;

import com.seek_with_sight.product.application.port.in.product.dto.LandingProducts;

public interface GetLandingProductsUseCase {
    LandingProducts get(int productsCount);
}
