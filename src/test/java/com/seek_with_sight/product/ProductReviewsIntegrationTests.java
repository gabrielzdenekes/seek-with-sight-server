package com.seek_with_sight.product;

import com.seek_with_sight.product.fixtures.ProductTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.review.AddProductReviewRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@AutoConfigureMockMvc
public class ProductReviewsIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ProductTestFixture productTestFixture;

    @Test
    public void shouldCalculateCorrectlyProductRatingBasedOnReviews() throws Exception {
        var product = (ProductResponseWithDetails) productTestFixture.createProduct().response().getData();
        var reviewsCount = 3;

        var reviewRequests = productTestFixture.addReviewsToProduct(reviewsCount, product.getId());

        var updatedProduct = productTestFixture.getProductById(product.getId()).getData();
        var sumOfRatings = reviewRequests.stream()
                .mapToInt(AddProductReviewRequest::rating)
                .sum();
        var expectedAverageRating = (double)sumOfRatings / reviewsCount;

        assertThat(updatedProduct.getReviewCount()).isEqualTo(reviewsCount);
        assertThat(updatedProduct.getAverageRating()).isEqualTo(expectedAverageRating);
    }
}
