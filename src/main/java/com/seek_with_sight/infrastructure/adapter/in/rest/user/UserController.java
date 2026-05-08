package com.seek_with_sight.infrastructure.adapter.in.rest.user;

import com.seek_with_sight.domain.port.in.user.CreateUserUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final UserRestMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        var createUserCommand = mapper.fromRequestToCreateCommand(userRequest);
        var createdUser = createUserUseCase.execute(createUserCommand);
        var responseUser = mapper.toResponse(createdUser);

        return new ResponseEntity<>(responseUser, HttpStatus.CREATED);
    }
}
