package com.seek_with_sight.utils.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@TestComponent
public class CustomerProfileTestFixture {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserTestFixture userFixture;

    @Autowired
    private ObjectMapper objectMapper;
}
