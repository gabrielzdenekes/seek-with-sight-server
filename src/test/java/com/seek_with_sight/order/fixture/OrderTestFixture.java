package com.seek_with_sight.order.fixture;

import com.seek_with_sight.order.infrastructure.adapter.in.rest.dto.OrderResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Component
public class OrderTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiResponse<OrderResponse> checkout(String accessToken) throws Exception {
        var result = mockMvc
                .perform(
                        post("/api/orders/checkout")
                                .header("Authorization", String.format("Bearer %s", accessToken))
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<OrderResponse>>() {
                }
        );

        return apiResponse;
    }
}
