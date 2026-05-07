package com.seek_with_sight.infrastructure.adapter.in.rest.ci_test;

import com.seek_with_sight.TestcontainersConfiguration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(TestcontainersConfiguration.class)
@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(CiController.class)
@Tag("slice-tests")
public class CiControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void ciEndpoint_ShouldReturnStatusOK() throws Exception {
        mockMvc.perform(get("/api/v1/ci"))
                .andExpect(status().isOk());
    }
}
