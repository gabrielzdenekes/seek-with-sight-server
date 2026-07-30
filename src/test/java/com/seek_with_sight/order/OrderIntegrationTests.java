package com.seek_with_sight.order;

import com.seek_with_sight.auth.AuthTestFixture;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginResponse;
import com.seek_with_sight.cart.CartTestFixture;
import com.seek_with_sight.order.domain.model.OrderStatus;
import com.seek_with_sight.order.domain.model.PaymentStatus;
import com.seek_with_sight.order.fixture.OrderTestFixture;
import com.seek_with_sight.order.infrastructure.adapter.in.rest.dto.OrderResponse;
import com.seek_with_sight.product.application.port.in.stock.ReserveStockUseCase;
import com.seek_with_sight.product.fixtures.ProductTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.profile.CustomerProfileTestFixture;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc()
public class OrderIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private OrderTestFixture orderTestFixture;

    @Autowired
    private ProductTestFixture productTestFixture;

    @Autowired
    private CartTestFixture cartTestFixture;

    @Autowired
    private CustomerProfileTestFixture customerTestFixture;

    @Autowired
    private AuthTestFixture authTestFixture;

    @Autowired
    private ReserveStockUseCase reserveStockUseCase;

    private LoginResponse loginData;

    @BeforeAll
    public void setup() throws Exception {
        var customerProfileData = customerTestFixture.createVerifiedCustomerProfile();

        loginData = authTestFixture.loginUser(customerProfileData.getEmail(), customerProfileData.getPassword());
    }

    @AfterEach
    public void cleanup() throws Exception {
        var clearResponse = cartTestFixture.clearCart(loginData.accessToken());
        assertThat(clearResponse.isSuccess()).isTrue();
    }

    @Test
    public void whenThereIsEnoughInventory_checkoutShouldCreateOrderSuccessfully() throws Exception {
        var products = createProducts(2, 10);

        addItemsToCart(products, 5);

        var checkoutResult = orderTestFixture.checkout(loginData.accessToken());

        assertThat(checkoutResult.isSuccess()).isTrue();

        var orderResponse = (OrderResponse)checkoutResult.getData();

        assertThat(orderResponse.orderNumber()).isNotBlank();
        assertThat(orderResponse.status()).isEqualTo(OrderStatus.PENDING);
        assertThat(orderResponse.paymentStatus()).isEqualTo(PaymentStatus.PENDING);
    }

    @Test
    public void whenTheInventoryIsNotEnough_checkoutShouldFail() throws Exception {
        var products = createProducts(2, 10);

        reserveStock(products, 5);

        addItemsToCart(products, 10);

        var checkoutResult = (ApiErrorResponse<?>) orderTestFixture.checkout(loginData.accessToken());

        assertThat(checkoutResult.isSuccess()).isFalse();
        assertThat(checkoutResult.getErrorCode()).isEqualTo(ErrorCode.INSUFFICIENT_STOCK);
    }

    private void reserveStock(List<ProductResponseWithDetails> products, int stockToReserve) {
        for (var product : products) {
            var variantId = product.getVariants().getFirst().id();

            reserveStockUseCase.reserve(variantId, stockToReserve);
        }
    }

    private void addItemsToCart(List<ProductResponseWithDetails> products, int quantity) throws Exception {
        for (var product : products) {
            var addResult = cartTestFixture.addVariantToCart(
                    product.getId(),
                    product.getVariants().getFirst().id(),
                    quantity,
                    loginData.accessToken()
            );

            assertThat(addResult.isSuccess()).isTrue();
        }
    }

    private List<ProductResponseWithDetails> createProducts(int variantsCount, int quantity) throws Exception {
        var products = new ArrayList<ProductResponseWithDetails>();

        for (var i = 0; i < variantsCount; i++) {
            var productRequest = productTestFixture.createProductRequest(null, quantity);
            var productResult = (ProductResponseWithDetails) productTestFixture
                    .createProduct(productRequest)
                    .response()
                    .getData();

            products.add(productResult);
        }

        return products;
    }
}
