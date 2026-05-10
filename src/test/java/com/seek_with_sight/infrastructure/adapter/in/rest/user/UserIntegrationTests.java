package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.TestDataUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class UserIntegrationTests extends IntegrationTestsBase {
    private static final String USER_CREATED_KEY = "user.created";
    private static final String PASSWORD_VALID_FORMAT_KEY = "user.validation.password.validFormat";
    private static final String VALIDATION_FAILED_KEY = "validation.failed";

    @Autowired
    private LocalizedMessageService messageService;

    @Test
    void whenSpanishLanguageIsRequired_userCreatedMessageShouldBeInSpanish() throws Exception {
        var userRequest = new UserRequest(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.generateRandomPassword()
        );
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locale = Locale.forLanguageTag("es");
        var request = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload, locale);
        var expectedMessageInSpanish = messageService.getMessage(USER_CREATED_KEY, locale);

        mockMvc.perform(request).andExpect(
                jsonPath("$.message").value(expectedMessageInSpanish)
        );
    }

    @Test
    void whenNonExistingLanguageIsRequired_userCreatedMessageShouldFallbackToDefaultEN() throws Exception {
        var userRequest = new UserRequest(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.generateRandomPassword()
        );

        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var request = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload, Locale.forLanguageTag("bg"));
        var expectedMessageInDefaultLang = messageService.getMessage(USER_CREATED_KEY, Locale.forLanguageTag("en"));

        mockMvc.perform(request).andExpect(
                jsonPath("$.message").value(expectedMessageInDefaultLang)
        );
    }

    @Test
    void whenPasswordHasInvalidFormat_ValidationMessageShouldBeDisplayedInCorrectLanguage() throws Exception {
        var userRequest = new UserRequest(
                TestDataUtils.generateRandomEmail(),
                TestDataUtils.INVALID_PASSWORD_FORMAT
        );
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locales = List.of(Locale.forLanguageTag("es"),  Locale.forLanguageTag("en"));

        for (var loc : locales) {
            var request = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload, loc);
            var globalValidationMessage = messageService.getMessage(VALIDATION_FAILED_KEY, loc);
            var expectedFieldErrorMessage = messageService.getMessage(PASSWORD_VALID_FORMAT_KEY, loc);

            mockMvc.perform(request)
                    .andExpect(jsonPath("$.message").value(globalValidationMessage))
                    .andExpect(jsonPath("$.data[0].fieldName").value("password"))
                    .andExpect(jsonPath("$.data[0].errorMessage").value(expectedFieldErrorMessage));
        }
    }
}
