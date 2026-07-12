package com.seek_with_sight.cart;

import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.AddCartItemRequest;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.CartResponse;
import com.seek_with_sight.cart.infrastructure.adapter.in.rest.dto.UpdateItemQuantityRequest;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

@TestComponent
public class CartTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiResponse<CartResponse> getCartForCurrentUser(String accessToken) throws Exception {
        var result = mockMvc
                .perform(
                        get("/api/cart")
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<CartResponse>>() {
                }
        );

        return apiResponse;
    }

    public ApiResponse<Void> addVariantToCart(
            UUID productId,
            UUID variantId,
            int quantity,
            String accessToken
    ) throws Exception {
        var dto = new AddCartItemRequest(productId, variantId, quantity);
        var payload = objectMapper.writeValueAsString(dto);
        var result = mockMvc
                .perform(
                        post("/api/cart/items")
                                .header("Authorization", "Bearer " + accessToken)
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<Void>>() {
                }
        );

        return apiResponse;
    }

    public ApiResponse<Void> updateItemQuantity(UUID variantId, int newQuantity, String accessToken) throws Exception {
        var dto = new UpdateItemQuantityRequest(newQuantity);
        var payload = objectMapper.writeValueAsString(dto);
        var result = mockMvc
                .perform(
                        patch("/api/cart/items/" + variantId)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(payload)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<Void>>() {
                }
        );

        return apiResponse;
    }

    public ApiResponse<Void> removeItem(UUID variantId, String accessToken) throws Exception {
        var result = mockMvc
                .perform(
                        delete("/api/cart/items/" + variantId)
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<Void>>() {
                }
        );

        return apiResponse;
    }

    public ApiResponse<Void> clearCart(String accessToken) throws Exception {
        var result = mockMvc
                .perform(
                        delete("/api/cart/items").header("Authorization", "Bearer " + accessToken)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<Void>>() {
                }
        );

        return apiResponse;
    }
}
