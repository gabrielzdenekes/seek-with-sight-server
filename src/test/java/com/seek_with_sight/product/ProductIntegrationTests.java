package com.seek_with_sight.product;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.UpdateProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.sql.SqlQueryCounterTestUtils;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ProductTestFixture productTestFixture;

    @Autowired
    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private SqlQueryCounterTestUtils sqlCounterUtils;

    @Test
    public void withValidData_productShouldBeCreatedAndRetrievedSuccessfully() throws Exception {
        var createResult = productTestFixture.createProduct();
        var responseData = (ProductResponse) createResult.response().getData();
        var requestData = createResult.request();
        var getResult = productTestFixture.getProductById(responseData.getId());
        var productResponse = getResult.getData();

        assertBaseProperties(productResponse, requestData);
        assertCategory(productResponse, requestData);
        assertBrand(productResponse, requestData);
    }

    @Test
    public void withInvalidData_ShouldReturnBadRequest() throws Exception {
        var createResult = productTestFixture.createProduct(null);

        assertThat(createResult.response().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

        var productRequest = new ProductRequest(null, null, null, null, null, null, null, null);
        createResult = productTestFixture.createProduct(productRequest);

        assertThat(createResult.response().getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void shouldUpdateProductSuccessfully() throws Exception {
        var createResult = productTestFixture.createProduct();
        var responseData = (ProductResponseWithDetails) createResult.response().getData();

        var newName = responseData.getName() + "1";
        var updateRequest = new UpdateProductRequest();

        updateRequest.setName(newName);

        var updatedProductResult = productTestFixture.updateProduct(responseData.getId(), updateRequest);
        var updatedProductData = updatedProductResult.response().getData();

        assertThat(updatedProductData.getName()).isEqualTo(newName);
    }

    @Test
    public void shouldUploadProductImageSuccessfully() throws Exception {
        var createResult = productTestFixture.createProduct();
        var responseData = (ProductResponseWithDetails) createResult.response().getData();
        var productId = responseData.getId();

        var uploadImageResult = productTestFixture.uploadProductImage(productId);

        assertThat(uploadImageResult.getData().getImages().size()).isEqualTo(1);

        uploadImageResult = productTestFixture.uploadProductImage(productId);

        assertThat(uploadImageResult.getData().getImages().size()).isEqualTo(2);
    }

    @Test
    public void whenCreateNewProduct_DefaultVariantShouldBeCreated() throws Exception {
        var createResult = productTestFixture.createProduct();
        var productResponseData = (ProductResponseWithDetails) createResult.response().getData();

        assertThat(productResponseData.getVariants().size()).isEqualTo(1);
    }

    private void assertBaseProperties(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(request.name());
        assertThat(result.getSlug()).isEqualTo(request.slug());
        assertThat(result.getShortDescription()).isEqualTo(request.shortDescription());
        assertThat(result.getDescription()).isEqualTo(request.description());
        assertThat(result.getStatus()).isEqualTo(request.status());
    }

    private void assertCategory(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getCategory().id()).isEqualTo(request.categoryId());
    }

    private void assertBrand(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getBrand().id()).isEqualTo(request.brandId());
    }
}
