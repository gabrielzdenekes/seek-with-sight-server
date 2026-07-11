package com.seek_with_sight.product;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponse;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.sql.SqlQueryCounterTestUtils;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
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
        var responseData = (ProductResponse)createResult.response().getData();
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
    @Transactional
    public void getProductByIdUseCase_shouldMakeFetchAllRelationshipsWithOneQuery() throws Exception {
        var createResult = productTestFixture.createProduct();
        var productId = ((ProductResponse)createResult.response().getData()).getId();

        sqlCounterUtils.assertSelectQueriesCount(
                () -> {
                    try {
                        productTestFixture.getProductById(productId);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                4
        );
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
