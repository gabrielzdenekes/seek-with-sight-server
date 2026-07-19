package com.seek_with_sight.product.fixtures;

import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.inventory.UpdateInventoryRequest;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@TestComponent
public class InventoryTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiResponse<Void> updateInventory(UUID variantId, int quantity) throws Exception {
        var request = new UpdateInventoryRequest(quantity);
        var payload = objectMapper.writeValueAsString(request);
        var result = mockMvc
                .perform(
                        put(String.format("/api/inventory/%s", variantId))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payload)
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
