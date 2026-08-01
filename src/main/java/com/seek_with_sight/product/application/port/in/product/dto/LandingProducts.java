package com.seek_with_sight.product.application.port.in.product.dto;

import com.seek_with_sight.order.domain.model.dto.BestSellingVariant;

import java.util.List;

public record LandingProducts(
        List<ProductListItem> onSale,
        List<ProductListItem> newArrivals,
        List<BestSellingVariant> bestSelling,
        List<BestReviewedProduct> bestReviewed
) {
}
