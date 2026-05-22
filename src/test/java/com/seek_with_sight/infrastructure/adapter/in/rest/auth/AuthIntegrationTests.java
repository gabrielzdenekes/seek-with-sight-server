package com.seek_with_sight.infrastructure.adapter.in.rest.auth;

import com.seek_with_sight.infrastructure.adapter.in.rest.auth.constants.AuthConstants;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.UserTestConstants;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.TestDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Locale;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthIntegrationTests extends IntegrationTestsBase {
    private static final String EMAIL_REQUIRED_KEY = "user.validation.email.required";
    private static final String PASSWORD_REQUIRED_KEY = "user.validation.password.required";

    @Autowired
    private LocalizedMessageService messageService;

    @Test
    void whenValidLoginDataIsProvided_userShouldReceiveJWTTokenAndRefreshTokenCookie() throws Exception {
        var userRequest = new UserRequest(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.generateRandomPassword()
        );
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locale = Locale.forLanguageTag("es");
        var createUserRequest = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload, locale);

        mockMvc.perform(createUserRequest).andExpect(status().isCreated());

        var loginRequest = postRequest(UserTestConstants.LOGIN_ENDPOINT, jsonPayload, locale);

        mockMvc.perform(loginRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.data.user.id").isNotEmpty())
                .andExpect(jsonPath("$.data.user.email").value(userRequest.email()))
                .andExpect(cookie().exists(AuthConstants.REFRESH_TOKEN_COOKIE_NAME))
                .andExpect(cookie().path(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, "/api/v1/auth"))
                .andExpect(cookie().httpOnly(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, true));
    }

    @Test
    void whenEmptyEmailAndPassword_shouldTriggerValidation() throws Exception {
        var userRequest = new UserRequest(
                "",
                ""
        );
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locales = List.of(Locale.forLanguageTag("es"),  Locale.forLanguageTag("en"));

        for (var loc : locales) {
            var request = postRequest(UserTestConstants.LOGIN_ENDPOINT, jsonPayload, loc);
            var emailMessage = messageService.getMessage(EMAIL_REQUIRED_KEY, loc);
            var passwordMessage = messageService.getMessage(PASSWORD_REQUIRED_KEY, loc);

            mockMvc.perform(request)
                    .andExpect(jsonPath("$.data", hasItem(
                            allOf(
                                    hasEntry("fieldName", "email"),
                                    hasEntry("errorMessage", emailMessage)
                            )
                    )))
                    .andExpect(jsonPath("$.data", hasItem(
                            allOf(
                                    hasEntry("fieldName", "password"),
                                    hasEntry("errorMessage", passwordMessage)
                            )
                    )));
        }
    }
}
