package com.seek_with_sight.authentication.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.authentication.domain.model.JwtLoginData;
import com.seek_with_sight.authentication.application.port.in.LoginCommand;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginRequest;
import com.seek_with_sight.authentication.infrastructure.adapter.in.rest.dto.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {
    LoginCommand fromLoginRequestToLoginCommand(LoginRequest loginRequest);

    LoginResponse fromJwtLoginDataToLoginResponse(JwtLoginData jwtLoginData);
}
