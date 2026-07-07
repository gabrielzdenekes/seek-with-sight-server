package com.seek_with_sight.media;

import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ImageIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private ImageTestFixture imageTestFixture;

    @Test
    public void imageUpload_shouldReturnCorrectImageData() throws Exception {
        var response = imageTestFixture.uploadImage();
        var imageData = response.getData();

        assertThat(imageData.id()).isNotNull();

        var imageResponse = imageTestFixture.resolveImage(imageData.url());

        assertThat(imageResponse.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(imageResponse.getHeader(HttpHeaders.CACHE_CONTROL))
                .isEqualTo("max-age=31536000, public, immutable");
    }
}
