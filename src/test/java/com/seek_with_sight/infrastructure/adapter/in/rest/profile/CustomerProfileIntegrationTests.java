package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.utils.fixture.AuthTestFixture;
import com.seek_with_sight.utils.fixture.UserTestFixture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;

@Import({
        UserTestFixture.class,
        AuthTestFixture.class,
})
@AutoConfigureMockMvc()
public class CustomerProfileIntegrationTests {
    @Autowired
    private AuthTestFixture authFixture;
}
