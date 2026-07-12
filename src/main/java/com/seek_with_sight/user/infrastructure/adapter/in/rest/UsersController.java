package com.seek_with_sight.user.infrastructure.adapter.in.rest;

import com.seek_with_sight.user.application.port.in.CreateUserUseCase;
import com.seek_with_sight.authorization.domain.model.role.RoleName;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UsersController {
    private final CreateUserUseCase createUserUseCase;
    private final UserRestMapper mapper;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        var createUserCommand = mapper.fromRequestToCreateCommand(userRequest);
        var createdUser = createUserUseCase.createUser(createUserCommand, List.of(RoleName.ROLE_CUSTOMER));

        return mapper.toResponse(createdUser);
    }
}
