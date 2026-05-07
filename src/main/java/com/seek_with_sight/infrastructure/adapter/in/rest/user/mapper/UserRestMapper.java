package com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper;

import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRestMapper {
    UserResponse toResponse(User user);

    User fromRequest(UserRequest userRequest);
}
