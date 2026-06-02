package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.application.port.in.user.CreateUserUseCase;
import com.seek_with_sight.domain.model.role.RoleName;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.annotation.ApiResponseDetails;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.CreateUserRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UserRestMapper mapper;

    @PostMapping
    @ApiResponseDetails(messageCode = "user.created", status =  HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest userRequest) {
        var createUserCommand = mapper.fromRequestToCreateCommand(userRequest);
        var createdUser = createUserUseCase.createUser(createUserCommand, List.of(RoleName.ROLE_CUSTOMER));

        return mapper.toResponse(createdUser);
    }
}
