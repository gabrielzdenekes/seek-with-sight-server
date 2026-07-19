package com.seek_with_sight.product.fixtures;

import com.seek_with_sight.media.ImageTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.UpdateVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.utils.data.RequestResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@TestComponent
public class ProductVariantTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ImageTestFixture imageTestFixture;

    @Value("classpath:test-images/test_image_01.jpg")
    private Resource imageResource;

    public ApiResponse<ProductVariantResponse> uploadImage(UUID productId, UUID variantId) throws Exception {

        var multipartFile = getImageMultipart();
        var result = mockMvc
                .perform(
                        multipart("/api/products/" + productId + "/variants/" + variantId + "/images")
                                .file(multipartFile)
                )
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ProductVariantResponse>>() {
                }
        );

        return apiResponse;
    }

    public RequestResponseData<UpdateVariantRequest, ApiResponse<ProductVariantResponse>> updateProductVariant(
            UUID productId,
            UUID variantId,
            UpdateVariantRequest dto
    ) throws Exception {
        var request = put("/api/products/" + productId + "/variants/" + variantId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));
        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ProductVariantResponse>>() {
                }
        );

        return new RequestResponseData<>(dto, apiResponse);
    }

    public RequestResponseData<ProductVariantRequest, ApiResponse<ProductVariantResponse>> createProductVariant(
            UUID productId,
            Integer quantity
    ) throws Exception {
        var dto = createProductVariantRequest(quantity);
        var request = post("/api/products/" + productId + "/variants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto));
        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ProductVariantResponse>>() {
                }
        );

        return new RequestResponseData<>(dto, apiResponse);
    }

    public RequestResponseData<ProductVariantRequest, ApiResponse<ProductVariantResponse>> updateProductVariant(
            UUID productId,
            UUID productVariantId,
            ProductVariantRequest updatedRequest
    ) throws Exception {
        var request = put("/api/products/" + productId + "/variants/" + productVariantId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedRequest));
        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ProductVariantResponse>>() {
                }
        );

        return new RequestResponseData<>(updatedRequest, apiResponse);
    }

    private MockMultipartFile getImageMultipart() throws IOException {
        return new MockMultipartFile(
                "file",
                "test_image_01.jpg",
                "image/jpeg",
                imageResource.getInputStream()
        );
    }

    private ProductVariantRequest createProductVariantRequest(Integer quantity) {
        return new ProductVariantRequest(
                ProductTestDataUtils.productName(),
                ProductTestDataUtils.sku(),
                ProductTestDataUtils.price(),
                quantity
        );
    }
}
