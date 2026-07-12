package com.seek_with_sight.utils;

import com.seek_with_sight.TestsConfiguration;
import com.seek_with_sight.cart.CartTestFixture;
import com.seek_with_sight.media.ImageTestFixture;
import com.seek_with_sight.product.ProductTestFixture;
import com.seek_with_sight.auth.AuthTestFixture;
import com.seek_with_sight.product.ProductVariantTestFixture;
import com.seek_with_sight.profile.CustomerProfileTestFixture;
import com.seek_with_sight.profile.SellerProfileTestFixture;
import com.seek_with_sight.user.UserTestFixture;
import com.seek_with_sight.utils.sql.SqlQueryCounterTestUtils;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Import({
        TestsConfiguration.class,
        ProductTestFixture.class,
        SqlQueryCounterTestUtils.class,
        UserTestFixture.class,
        SellerProfileTestFixture.class,
        AuthTestFixture.class,
        CustomerProfileTestFixture.class,
        ImageTestFixture.class,
        ProductVariantTestFixture.class,
        CartTestFixture.class
})
@SpringBootTest
@Tag("integration-tests")
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
public abstract class IntegrationTestsBase {
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected MockHttpServletRequestBuilder postRequest(String url, String jsonPayload) {
        return post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload);
    }
}
