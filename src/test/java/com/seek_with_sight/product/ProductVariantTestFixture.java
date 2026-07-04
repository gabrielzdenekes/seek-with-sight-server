package com.seek_with_sight.product;

import com.seek_with_sight.media.ImageTestFixture;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.ProductVariantRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.request.variant.VariantOptionRequest;
import com.seek_with_sight.product.infrastructure.adapter.in.rest.dto.response.ProductVariantResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.utils.data.RequestResponseData;
import com.seek_with_sight.utils.data.TestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestComponent
public class ProductVariantTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ImageTestFixture imageTestFixture;

    public RequestResponseData<ProductVariantRequest, ApiResponse<ProductVariantResponse>> createProductVariant(UUID productId) throws Exception {
        var dto = createProductVariantRequest();
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

    private ProductVariantRequest createProductVariantRequest() throws Exception {
        var selectedOptions = getSelectedOptionsRequestData();
        var imageIds = getImageIds();

        return new ProductVariantRequest(
                ProductTestDataUtils.productName(),
                ProductTestDataUtils.sku(),
                ProductTestDataUtils.barcode(),
                ProductTestDataUtils.price(),
                ProductTestDataUtils.price(),
                true,
                0,
                ProductTestDataUtils.weight(),
                "kg",
                "cm",
                ProductTestDataUtils.dimension(),
                ProductTestDataUtils.dimension(),
                ProductTestDataUtils.dimension(),
                imageIds,
                selectedOptions
        );
    }

    private List<UUID> getImageIds() throws Exception {
        var imageIds = new ArrayList<UUID>();

        for (var i = 0; i < TestDataUtils.randomIntegerBetween(1, 4); i++) {
            var imageResult = imageTestFixture.uploadImage();
            imageIds.add(imageResult.getData().id());
        }

        return imageIds;
    }

    private List<VariantOptionRequest> getSelectedOptionsRequestData() {
        var selectedOptions = IntStream.range(0, TestDataUtils.randomIntegerBetween(1, 4))
                .mapToObj(i -> new VariantOptionRequest(
                        TestDataUtils.word(),
                        TestDataUtils.word(),
                        i
                ))
                .toList();

        return selectedOptions;
    }
}
