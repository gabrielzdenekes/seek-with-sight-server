package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.TestcontainersConfiguration;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import tools.jackson.databind.ObjectMapper;

import java.util.Locale;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@Tag("integration-tests")
@ActiveProfiles("test")
public class UserIntegrationTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LocalizedMessageService messageService;

    @Test
    void whenSpanishLanguageIsRequired_userCreatedMessageShouldBeInSpanish() throws Exception {
        var userRequest = new UserRequest(UUID.randomUUID() + "@mail.com", "password1@P");
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var request = createUserHttpRequest(jsonPayload, "es");
        var expectedMessageInSpanish = messageService.getMessage("user.created", Locale.of("es"));

        mockMvc.perform(request)
                .andExpect(jsonPath("$.message").value(expectedMessageInSpanish));
    }

    @Test
    void whenNonExistingLanguageIsRequired_userCreatedMessageShouldFallbackToDefaultEN() throws Exception {
        var userRequest = new UserRequest(UUID.randomUUID() + "@mail.com", "password1@P");
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var request = createUserHttpRequest(jsonPayload, "bg");
        var expectedMessageInSpanish = messageService.getMessage("user.created", Locale.of("en"));

        mockMvc.perform(request)
                .andExpect(jsonPath("$.message").value(expectedMessageInSpanish));
    }

    private MockHttpServletRequestBuilder createUserHttpRequest(String jsonPayload, String language) {
        return post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT_LANGUAGE, language)
                .content(jsonPayload);
    }
}
