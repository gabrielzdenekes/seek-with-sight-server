package com.seek_with_sight.utils.fixture;

import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CreateSellerRequest;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.SellerProfileResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.seek_with_sight.utils.data.ProfileTestDataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

@TestComponent
public class SellerProfileTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserTestFixture userFixture;

    @Autowired
    private ObjectMapper objectMapper;

    public CreateSellerRequest createVerifiedSellerProfile() throws Exception {
        var dto = new CreateSellerRequest(
                ProfileTestDataUtils.email(),
                ProfileTestDataUtils.validPassword()
        );

        dto.setBusinessName(ProfileTestDataUtils.businessName());
        dto.setTaxId(ProfileTestDataUtils.taxId());
        dto.setBusinessAddress(ProfileTestDataUtils.address());

        var jsonPayload = objectMapper.writeValueAsString(dto);
        var request = post("/api/seller-profile")
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

    public SellerProfileResponse getCurrentUserSellerProfile(String accessToken) throws Exception {
        var request = get("/api/seller-profile/me")
                .header("Authorization", "Bearer " + accessToken);

        var result = mockMvc.perform(request).andReturn();
        var apiResponse = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<ApiResponse<SellerProfileResponse>>() {}
        );

        return apiResponse.getData();
    }
}
