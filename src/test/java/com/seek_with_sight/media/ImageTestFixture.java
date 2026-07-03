package com.seek_with_sight.media;

import com.seek_with_sight.media.infrastructure.in.rest.dto.ImageResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

@TestComponent
public class ImageTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:test-images/test_image_01.jpg")
    private Resource imageResource;

    @Autowired
    private ObjectMapper objectMapper;

    public ApiResponse<ImageResponse> uploadImage() throws Exception {
        var multipartFile = new MockMultipartFile(
                "file",
                "test_image_01.jpg",
                "image/jpeg",
                imageResource.getInputStream()
        );

        var result = mockMvc.perform(multipart("/api/images")
                .file(multipartFile))
                .andReturn();

        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<ImageResponse>>() {}
        );

        return apiResponse;
    }
}
