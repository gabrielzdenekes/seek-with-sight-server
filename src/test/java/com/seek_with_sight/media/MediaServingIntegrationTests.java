package com.seek_with_sight.media;

import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


public class MediaServingIntegrationTests extends IntegrationTestsBase {
    @Test
    public void shouldReturnForbiddenWhenPathTraversalIsAttempted() throws Exception {
        var uri = new URI("/uploads/images/..%2F..%2F..%2Fetc%2Fpasswd");

        mockMvc.perform(get(uri))
                .andExpect(status().isForbidden());
    }
}
