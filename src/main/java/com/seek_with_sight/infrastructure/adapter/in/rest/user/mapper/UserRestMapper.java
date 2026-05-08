package com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.domain.port.in.user.CreateUserCommand;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    UserResponse toResponse(User user);

    @Mapping(source = "password", target = "rawPassword")
    CreateUserCommand fromRequestToCreateCommand(UserRequest userRequest);
}
