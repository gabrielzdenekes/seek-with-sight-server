package com.seek_with_sight.search;

import com.seek_with_sight.search.infrastructure.adapter.in.rest.dto.ProductSearchResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@TestComponent
public class ProductSearchTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiResponse<List<ProductSearchResponse>> searchByText(String text) throws Exception {
        var result = mockMvc
                .perform(
                        get("/api/products/search?query=" + text)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<List<ProductSearchResponse>>>() {
                }
        );

        return apiResponse;
    }
}
