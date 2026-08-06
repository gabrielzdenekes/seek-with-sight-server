package com.seek_with_sight.product.application.port.in.product.dto;

import com.seek_with_sight.product.domain.model.product.BestReviewedProductItem;
import com.seek_with_sight.product.domain.model.product.BestSellingVariantItem;
import com.seek_with_sight.product.domain.model.product.ProductListItem;

import java.util.List;

public record LandingProducts(
        List<ProductListItem> onSale,
        List<ProductListItem> newArrivals,
        List<BestSellingVariantItem> bestSelling,
        List<BestReviewedProductItem> bestReviewed
) {
}
