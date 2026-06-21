package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.fixture.AuthTestFixture;
import com.seek_with_sight.utils.fixture.SellerProfileTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc()
public class SellerProfileIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private SellerProfileTestFixture sellerFixture;

    @Autowired
    private AuthTestFixture authFixture;

    @Test
    public void whenValidLoginDataIsProvided_sellerProfileShouldBeCreatedAndAccessible() throws Exception {
        var createSellerDto = sellerFixture.createVerifiedSellerProfile();
        var loginResponse = authFixture.loginUser(createSellerDto.getEmail(), createSellerDto.getPassword());
        var sellerProfile = sellerFixture.getCurrentUserSellerProfile(loginResponse.accessToken());

        assertThat(sellerProfile.user().email()).isEqualTo(createSellerDto.getEmail());
        assertThat(sellerProfile.businessName()).isEqualTo(createSellerDto.getBusinessName());
        assertThat(sellerProfile.businessAddress()).isEqualTo(createSellerDto.getBusinessAddress());
        assertThat(sellerProfile.taxId()).isEqualTo(createSellerDto.getTaxId());
    }
}
