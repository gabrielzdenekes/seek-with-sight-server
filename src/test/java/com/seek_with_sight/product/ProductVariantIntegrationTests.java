package com.seek_with_sight.product;

import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
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
        var productId = productData.response().getData().getId();
        var productVariant = productVariantTestFixture.createProductVariant(productId);

        var createRequestData = productVariant.request();
        var responseData = productVariant.response().getData();

        assertThat(responseData.id()).isNotNull();
        assertThat(responseData.images().size()).isEqualTo(createRequestData.imageIds().size());
        assertThat(responseData.selectedOptions().size()).isEqualTo(createRequestData.selectedOptions().size());
    }

    @Test
    public void updateProductVariant_withValidData_shouldBeSuccessful() throws Exception {
        var productData = productTestFixture.createProduct();
        var productId = productData.response().getData().getId();
        var productVariant = productVariantTestFixture.createProductVariant(productId);
        var createRequestData = productVariant.request();
        var responseData = productVariant.response().getData();
        var newImages = productVariantTestFixture.getImageIds();
        var newOptions = productVariantTestFixture.getSelectedOptionsRequestData();

        var updateRequestData = new ProductVariantRequest(
                createRequestData.title(),
                createRequestData.sku(),
                createRequestData.barcode(),
                createRequestData.price(),
                createRequestData.compareAtPrice(),
                createRequestData.isActive(),
                createRequestData.sortOrder(),
                createRequestData.weight(),
                createRequestData.weightUnit(),
                createRequestData.dimensionUnit(),
                createRequestData.length(),
                createRequestData.width(),
                createRequestData.height(),
                newImages,
                newOptions
        );

        var updatedVariant = productVariantTestFixture.updateProductVariant(productId, responseData.id(), updateRequestData);
        var updatedImages = updatedVariant.response().getData().images();

        assertThat(updatedImages.size()).isEqualTo(newImages.size());

        for (var i : newImages) {
            var updatedImage = updatedImages
                    .stream()
                    .filter(x -> x.id().equals(i))
                    .findFirst();

            assertThat(updatedImage.isPresent()).isTrue();
        }

        var updatedOptions = updatedVariant.response().getData().selectedOptions();

        assertThat(updatedOptions.size()).isEqualTo(newOptions.size());

        for (var o : newOptions) {
            var updatedOption = updatedOptions
                    .stream()
                    .filter(x -> x.name().equals(o.name()) && x.value().equals(o.value()))
                    .findFirst();

            assertThat(updatedOption.isPresent()).isTrue();
        }
    }
}
