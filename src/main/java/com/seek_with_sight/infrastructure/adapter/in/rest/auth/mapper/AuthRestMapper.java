package com.seek_with_sight.infrastructure.adapter.in.rest.auth.mapper;

import com.seek_with_sight.domain.model.auth.JwtLoginData;
import com.seek_with_sight.application.port.in.auth.LoginCommand;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.auth.dto.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {
    LoginCommand fromLoginRequestToLoginCommand(LoginRequest loginRequest);

    LoginResponse fromJwtLoginDataToLoginResponse(JwtLoginData jwtLoginData);
}
