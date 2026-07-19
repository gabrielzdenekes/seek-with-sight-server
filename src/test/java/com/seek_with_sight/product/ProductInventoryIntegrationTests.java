package com.seek_with_sight.product;

import com.seek_with_sight.product.application.port.in.product.GetProductByIdUseCase;
import com.seek_with_sight.product.application.port.out.ProductInventoryRepositoryPort;
import com.seek_with_sight.product.fixtures.InventoryTestFixture;
import com.seek_with_sight.product.fixtures.ProductTestFixture;
import com.seek_with_sight.product.fixtures.ProductVariantTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProductInventoryIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ProductTestFixture productTestFixture;

    @Autowired
    private GetProductByIdUseCase getProductByIdUseCase;

    @Autowired
    private ProductInventoryRepositoryPort inventoryRepo;

    @Autowired
    private ProductVariantTestFixture variantTestFixture;

    @Autowired
    private InventoryTestFixture inventoryTestFixture;

    @Test
    @Transactional
    public void createProductWithInitialQuantity_shouldCreateDefaultVariantWithInitialInventory() throws Exception {
        var initialQuantity = 10;
        var productRequest = productTestFixture.createProductRequest(null, initialQuantity);
        var productResult = (ProductResponseWithDetails) productTestFixture
                .createProduct(productRequest)
                .response()
                .getData();
        var defaultVariant = productResult.getVariants().getFirst();

        var inventory = inventoryRepo.findByVariantIdForUpdate(defaultVariant.id()).get();

        assertThat(inventory.getQuantity()).isEqualTo(initialQuantity);
    }

    @Test
    @Transactional
    public void createProductVariantWithInitialQuantity_shouldCreateInventory() throws Exception {
        var productResult = (ProductResponseWithDetails) productTestFixture
                .createProduct()
                .response()
                .getData();

        var initialQuantity = 15;

        var variantResult = variantTestFixture
                .createProductVariant(productResult.getId(), initialQuantity)
                .response()
                .getData();

        var inventory = inventoryRepo.findByVariantIdForUpdate(variantResult.id()).get();

        assertThat(inventory.getQuantity()).isEqualTo(initialQuantity);
    }

    @Test
    @Transactional
    public void updateInventory_shouldBeSuccessful() throws Exception {
        var productResult = (ProductResponseWithDetails) productTestFixture
                .createProduct()
                .response()
                .getData();
        var defaultVariant = productResult.getVariants().getFirst();
        var newQuantity = 33;

        var updateResult = inventoryTestFixture.updateInventory(defaultVariant.id(), newQuantity);

        assertThat(updateResult.isSuccess()).isTrue();

        var inventory = inventoryRepo.findByVariantIdForUpdate(defaultVariant.id()).get();

        assertThat(inventory.getQuantity()).isEqualTo(newQuantity);
    }
}
