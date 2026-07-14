package com.seek_with_sight.search;

import com.seek_with_sight.product.ProductTestFixture;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductSearchIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ProductTestFixture productTestFixture;

    @Autowired
    private ProductSearchTestFixture productSearchTestFixture;

    @Test
    public void whenSearchedByGivenWord_shouldReturnCorrectResults() throws Exception {
        var searchWord = "spcje";
        var productsCount = 3;

        createProductsWithNameToSearchFor(searchWord, productsCount);

        var searchResult = productSearchTestFixture.searchByText(searchWord);
        var products = searchResult.getData();

        assertThat(products.size()).isEqualTo(productsCount);

        for (var product : products) {
            assertThat(product.name().contains(searchWord)).isTrue();
        }
    }

    private void createProductsWithNameToSearchFor(String keyword, int productsCount) throws Exception {
        for (var i = 0; i < productsCount; i++) {
            var request = productTestFixture.createProductRequest("Product " + keyword + " " + UUID.randomUUID());

            productTestFixture.createProduct(request);
        }
    }
}
