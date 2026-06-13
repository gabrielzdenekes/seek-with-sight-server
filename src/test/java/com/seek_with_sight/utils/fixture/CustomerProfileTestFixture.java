package com.seek_with_sight.utils.fixture;

import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CreateCustomerRequest;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CustomerProfileResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.seek_with_sight.utils.data.TestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@TestComponent
public class CustomerProfileTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserTestFixture userFixture;

    @Autowired
    private ObjectMapper objectMapper;

    public CreateCustomerRequest createVerifiedCustomerProfile() throws Exception {
        var dto = new CreateCustomerRequest(
                TestDataUtils.email(),
                TestDataUtils.validPassword()
        );

        dto.setFirstName(TestDataUtils.firstName());
        dto.setPhone(TestDataUtils.phoneNumber());

        var jsonPayload = objectMapper.writeValueAsString(dto);
        var request = post("/api/customer-profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload);
        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<UserResponse>>() {}
        );

        userFixture.verifyUser(apiResponse.getData().id());

        return dto;
    }

    public CustomerProfileResponse getCurrentUserCustomerProfile(String accessToken) throws Exception {
        var request = get("/api/customer-profile/me")
                .header("Authorization", "Bearer " + accessToken);

        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<CustomerProfileResponse>>() {}
        );

        return apiResponse.getData();
    }
}
