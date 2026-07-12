package com.seek_with_sight.auth;

import com.seek_with_sight.user.application.port.out.UserRepositoryPort;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.constants.AuthConstants;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.service.base.LocalizedMessageService;
import com.seek_with_sight.user.UserTestConstants;
import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.user.UserTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;

import java.util.Locale;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.cookie;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class AuthIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private UserRepositoryPort userRepo;

    @Autowired
    private LocalizedMessageService messageService;

    @Autowired
    private UserTestFixture userFixture;

    @Test
    void whenValidLoginDataIsProvided_userShouldReceiveJWTTokenAndRefreshTokenCookie() throws Exception {
        var userRequest = userFixture.createUserRequest();
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locale = Locale.forLanguageTag("es");
        var createUserRequest = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload);

        mockMvc.perform(createUserRequest).andExpect(status().isCreated());
        makeUserVerified(userRequest.getEmail());

        var loginRequest = postRequest(UserTestConstants.LOGIN_ENDPOINT, jsonPayload);

        mockMvc.perform(loginRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.accessToken").isNotEmpty())
                .andExpect(jsonPath("$.data.user.id").isNotEmpty())
                .andExpect(jsonPath("$.data.user.email").value(userRequest.getEmail()))
                .andExpect(cookie().exists(AuthConstants.REFRESH_TOKEN_COOKIE_NAME))
                .andExpect(cookie().path(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, "/api/auth"))
                .andExpect(cookie().httpOnly(AuthConstants.REFRESH_TOKEN_COOKIE_NAME, true));
    }

    @Test
    void whenUserIsNotVerified_loginIsNotPermitted() throws Exception {
        var userRequest = userFixture.createUserRequest();
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var locale = Locale.forLanguageTag("es");
        var createUserRequest = postRequest(UserTestConstants.USER_ENDPOINT, jsonPayload);

        mockMvc.perform(createUserRequest).andExpect(status().isCreated());

        var loginRequest = postRequest(UserTestConstants.LOGIN_ENDPOINT, jsonPayload);

        mockMvc.perform(loginRequest)
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.errorCode").value("EMAIL_NOT_VERIFIED"))
                .andExpect(jsonPath("$.status").value(HttpStatus.FORBIDDEN.value()))
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void whenEmptyEmailAndPassword_shouldTriggerValidation() throws Exception {
        var userRequest = userFixture.createUserRequestEmptyFields();
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var request = postRequest(UserTestConstants.LOGIN_ENDPOINT, jsonPayload);

        mockMvc.perform(request)
                .andExpect(jsonPath("$.data", hasItem(
                        allOf(
                                hasEntry("fieldName", "email"),
                                hasEntry("errorMessage", "Email is required")
                        )
                )))
                .andExpect(jsonPath("$.data", hasItem(
                        allOf(
                                hasEntry("fieldName", "password"),
                                hasEntry("errorMessage", "Password is required")
                        )
                )));
    }

    private void makeUserVerified(String email) {
        var user = userRepo.findByEmailIgnoreCase(email).orElseThrow();

        user.setEmailVerified(true);
        userRepo.save(user);
    }
}
