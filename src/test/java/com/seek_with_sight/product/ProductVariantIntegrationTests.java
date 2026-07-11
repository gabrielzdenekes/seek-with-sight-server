package com.seek_with_sight.product;

import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.UpdateVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductVariantIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ProductVariantTestFixture productVariantTestFixture;

    @Autowired
    private ProductTestFixture productTestFixture;

    @Test
    public void createProductVariant_withValidData_shouldBeSuccessful() throws Exception {
        var productData = productTestFixture.createProduct();
        var productId = ((ProductResponse)productData.response().getData()).getId();
        var productVariant = productVariantTestFixture.createProductVariant(productId);

        var createRequestData = productVariant.request();
        var responseData = productVariant.response().getData();

        assertThat(responseData.id()).isNotNull();
    }

    @Test
    public void updateProductVariant_withValidData_shouldBeSuccessful() throws Exception {
        var productData = productTestFixture.createProduct();
        var responseData = ((ProductResponse)productData.response().getData());
        var productId = responseData.getId();
        var productVariant = productVariantTestFixture.createProductVariant(productId);

        var updateRequest = new UpdateVariantRequest(
                productVariant.response().getData().title(),
                null,
                null
        );

        var updatedProductData = productVariantTestFixture.updateProductVariant(
                productId,
                productVariant.response().getData().id(),
                updateRequest
        );

        assertThat(updatedProductData.response().getData().title()).isEqualTo(updateRequest.title());
    }
}
