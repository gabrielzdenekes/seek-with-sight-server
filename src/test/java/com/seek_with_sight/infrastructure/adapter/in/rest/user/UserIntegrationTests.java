package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.TestcontainersConfiguration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Tag("integration-tests")
public class UserIntegrationTests {
    @Test
    void intTest() { }
}
