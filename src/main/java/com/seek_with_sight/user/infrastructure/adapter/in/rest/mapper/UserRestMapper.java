package com.seek_with_sight.user.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.user.domain.model.User;
import com.seek_with_sight.user.application.port.in.CreateUserCommand;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.CreateUserRequest;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    UserResponse toResponse(User user);

    @Mapping(source = "password", target = "rawPassword")
    CreateUserCommand fromRequestToCreateCommand(CreateUserRequest userRequest);
}
