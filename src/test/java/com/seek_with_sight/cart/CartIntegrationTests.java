package com.seek_with_sight.cart;

import com.seek_with_sight.auth.AuthTestFixture;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginResponse;
import com.seek_with_sight.product.fixtures.ProductTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductResponseWithDetails;
import com.seek_with_sight.profile.CustomerProfileTestFixture;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc()
public class CartIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private CustomerProfileTestFixture customerTestFixture;

    @Autowired
    private AuthTestFixture authTestFixture;

    @Autowired
    private CartTestFixture cartTestFixture;

    @Autowired
    private ProductTestFixture productTestFixture;

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
    public void shouldGetCartForCurrentUserSuccessfully() throws Exception {
        var cartResponse = cartTestFixture.getCartForCurrentUser(loginData.accessToken());

        assertThat(cartResponse.isSuccess()).isTrue();
    }

    @Test
    public void shouldAddItemToCartSuccessfully() throws Exception {
        var productData = (ProductResponseWithDetails) productTestFixture.createProduct().response().getData();
        var variant = productData.getVariants().getFirst();
        var quantity = 3;

        var addItemResponse = cartTestFixture.addVariantToCart(
                productData.getId(),
                variant.id(),
                quantity,
                loginData.accessToken()
        );

        assertThat(addItemResponse.isSuccess()).isTrue();

        var cartResponse = cartTestFixture.getCartForCurrentUser(loginData.accessToken());
        var items = cartResponse.getData().items();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.getFirst().variant().id()).isEqualTo(variant.id());
        assertThat(items.getFirst().quantity()).isEqualTo(quantity);
    }

    @Test
    public void shouldUpdateItemQuantitySuccessfully() throws Exception {
        var productData = (ProductResponseWithDetails) productTestFixture.createProduct().response().getData();
        var variant = productData.getVariants().getFirst();
        var quantity = 3;

        cartTestFixture.addVariantToCart(
                productData.getId(),
                variant.id(),
                quantity,
                loginData.accessToken()
        );

        var newQuantity = quantity * 2;

        var updateQuantityData = cartTestFixture.updateItemQuantity(
                variant.id(),
                newQuantity,
                loginData.accessToken()
        );

        assertThat(updateQuantityData.isSuccess()).isTrue();

        var cartResponse = cartTestFixture.getCartForCurrentUser(loginData.accessToken());
        var items = cartResponse.getData().items();

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.getFirst().variant().id()).isEqualTo(variant.id());
        assertThat(items.getFirst().quantity()).isEqualTo(newQuantity);
    }

    @Test
    public void shouldRemoveItemSuccessfully() throws Exception {
        var productData = (ProductResponseWithDetails) productTestFixture.createProduct().response().getData();
        var variant = productData.getVariants().getFirst();
        var quantity = 3;

        cartTestFixture.addVariantToCart(
                productData.getId(),
                variant.id(),
                quantity,
                loginData.accessToken()
        );

        var removeItemResponse = cartTestFixture.removeItem(variant.id(), loginData.accessToken());

        assertThat(removeItemResponse.isSuccess()).isTrue();

        var cartResponse = cartTestFixture.getCartForCurrentUser(loginData.accessToken());
        var items = cartResponse.getData().items();

        assertThat(items.size()).isEqualTo(0);
    }
}
