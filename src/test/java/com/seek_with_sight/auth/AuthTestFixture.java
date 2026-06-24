package com.seek_with_sight.auth;

import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.user.UserTestConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@TestComponent
@RequiredArgsConstructor
public class AuthTestFixture {
    private final ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    public LoginResponse loginUser(String email, String password) throws Exception {
        var loginRequestDto = new LoginRequest(email, password);
        var jsonPayload = objectMapper.writeValueAsString(loginRequestDto);
        var request = post(UserTestConstants.LOGIN_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload);
        var mvcResult = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                mvcResult.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<LoginResponse>>() {}
        );

        return apiResponse.getData();
    }
}
