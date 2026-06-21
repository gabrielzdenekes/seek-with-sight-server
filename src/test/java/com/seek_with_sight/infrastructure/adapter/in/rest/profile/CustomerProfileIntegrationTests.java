package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.utils.IntegrationTestsBase;
import com.seek_with_sight.utils.fixture.AuthTestFixture;
import com.seek_with_sight.utils.fixture.CustomerProfileTestFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc()
public class CustomerProfileIntegrationTests extends IntegrationTestsBase {
    @Autowired
    private AuthTestFixture authFixture;

    @Autowired
    private CustomerProfileTestFixture customerFixture;

    @Test
    public void whenValidLoginDataIsProvided_customerProfileShouldBeCreatedAndAccessible() throws Exception {
        var createCustomerDto = customerFixture.createVerifiedCustomerProfile();
        var loginResponse = authFixture.loginUser(createCustomerDto.getEmail(), createCustomerDto.getPassword());
        var customerProfile = customerFixture.getCurrentUserCustomerProfile(loginResponse.accessToken());

        assertThat(customerProfile.user().email()).isEqualTo(createCustomerDto.getEmail());
        assertThat(customerProfile.firstName()).isEqualTo(createCustomerDto.getFirstName());
        assertThat(customerProfile.phone()).isEqualTo(createCustomerDto.getPhone());
    }
}
