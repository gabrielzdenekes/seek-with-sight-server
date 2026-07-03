package com.seek_with_sight.product;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.product.ProductRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.sql.SqlQueryCounterTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

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
        var responseData = createResult.response().getData();
        var requestData = createResult.request();
        var getResult = productTestFixture.getProductById(responseData.getId());
        var productResponse = getResult.getData();

        assertBaseProperties(productResponse, requestData);
        assertCategory(productResponse, requestData);
        assertBrand(productResponse, requestData);
        assertSeo(productResponse, requestData);
        assertImages(productResponse, requestData);
    }

    @Test
    @Transactional
    public void getProductByIdUseCase_shouldMakeFetchAllRelationshipsWithOneQuery() throws Exception {
        var createResult = productTestFixture.createProduct();
        var productId = createResult.response().getData().getId();

        /*
        Total relationships: Category, Brand, Tags, Images, Variants, Attributes, Seo
        4 select queries are made:
         - for the product
         - for the tags
         - for the imageIds
         - for product variants
         */
        sqlCounterUtils.assertSelectQueriesCount(
                () -> {
                    try {
                        productTestFixture.getProductById(createResult.response().getData().getId());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                5
        );
    }

    private void assertBaseProperties(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getId()).isNotNull();
        assertThat(result.getName()).isEqualTo(request.name());
        assertThat(result.getSlug()).isEqualTo(request.slug());
        assertThat(result.getShortDescription()).isEqualTo(request.shortDescription());
        assertThat(result.getDescription()).isEqualTo(request.description());
        assertThat(result.getStatus()).isEqualTo(request.status());
        assertThat(result.getCurrencyCode()).isEqualTo(request.currencyCode());
        assertThat(result.getWeight()).isEqualTo(request.weight());
        assertThat(result.getWeightUnit()).isEqualTo(request.weightUnit());
        assertThat(result.getRequiresShipping()).isEqualTo(request.requiresShipping());
        assertThat(result.getIsDigital()).isEqualTo(request.isDigital());
        assertThat(result.getTaxClass()).isEqualTo(request.taxClass());
        assertThat(result.getBasePrice()).isEqualTo(request.basePrice());
        assertThat(result.getCompareAtPrice()).isEqualTo(request.compareAtPrice());
    }

    private void assertCategory(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getCategory().id()).isEqualTo(request.categoryId());
    }

    private void assertBrand(ProductResponseWithDetails result, ProductRequest request) {
        assertThat(result.getBrand().id()).isEqualTo(request.brandId());
    }

    private void assertSeo(ProductResponseWithDetails result, ProductRequest request) {
        var responseSeo = result.getSeo();
        var requestSeo = request.seo();

        assertThat(responseSeo.id()).isNotNull();
        assertThat(responseSeo.metaTitle()).isEqualTo(requestSeo.metaTitle());
        assertThat(responseSeo.metaDescription()).isEqualTo(requestSeo.metaDescription());
        assertThat(responseSeo.ogTitle()).isEqualTo(requestSeo.ogTitle());
        assertThat(responseSeo.ogDescription()).isEqualTo(requestSeo.ogDescription());
        assertThat(responseSeo.ogImageUrl()).isEqualTo(requestSeo.ogImageUrl());
    }

    private void assertImages(ProductResponseWithDetails result, ProductRequest request) {
        var actualImages = result.getImages().toArray(ImageResponse[]::new);
        var expectedImages = request.imageIds().toArray(UUID[]::new);

        assertThat(actualImages.length).isEqualTo(expectedImages.length);

        for (int i = 0; i < expectedImages.length; i++) {
            var actImgId = actualImages[i].id();
            var expImgId = expectedImages[i];

            assertThat(actImgId).isEqualTo(expImgId);
        }
    }
}
