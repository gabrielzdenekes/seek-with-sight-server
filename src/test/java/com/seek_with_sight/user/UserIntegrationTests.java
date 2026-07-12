package com.seek_with_sight.user;

import com.seek_with_sight.shared.infrastructure.adapter.in.rest.service.base.LocalizedMessageService;
import com.seek_with_sight.utils.IntegrationTestsBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Import({UserTestFixture.class})
public class UserIntegrationTests extends IntegrationTestsBase {
    private static final String USER_CREATED_KEY = "user.created";

    @Autowired
    private LocalizedMessageService messageService;

    @Autowired
    private UserTestFixture userFixture;

    @Test
    void whenPasswordHasInvalidFormat_ValidationMessageShouldBeDisplayed() throws Exception {
        var userRequest = userFixture.createUserRequestInvalidPassword();
        var jsonPayload = objectMapper.writeValueAsString(userRequest);

        var request = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload);

        mockMvc.perform(request)
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.data[0].fieldName").value("password"))
                .andExpect(jsonPath("$.data[0].errorMessage")
                        .value("Password must include at least one uppercase letter, one lowercase letter, one number, and one special character"));
    }
}
