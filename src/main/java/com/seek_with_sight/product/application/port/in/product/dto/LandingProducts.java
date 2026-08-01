package com.seek_with_sight.product.application.port.in.product.dto;

import java.util.List;

public record LandingProducts(
        List<DiscountedProductListItem> productsOnSale
) {
}
