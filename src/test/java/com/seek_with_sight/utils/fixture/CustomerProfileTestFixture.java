package com.seek_with_sight.utils.fixture;

import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateCustomerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ApiResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import com.seek_with_sight.utils.data.TestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

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
}
