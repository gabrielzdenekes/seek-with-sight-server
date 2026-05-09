package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.user.CreateUserCommand;
import com.seek_with_sight.domain.port.in.user.CreateUserUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper.UserRestMapper;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
@Tag("slice-tests")
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateUserUseCase userService;

    @MockitoBean
    private UserRestMapper userMapper;

    @MockitoBean
    private LocalizedMessageService messageService;

    @Test
    void postUser_shouldCreateUserSuccessfully() throws Exception {
        when(userService.execute(any(CreateUserCommand.class))).thenReturn(new User());

        var userRequest = new UserRequest("email1@email.com", "password1@P");
        var jsonPayload = objectMapper.writeValueAsString(userRequest);
        var request = post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonPayload);

        mockMvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true));
    }
}
