package com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper;

import com.seek_with_sight.application.port.in.profile.command.CreateCustomerProfileCommand;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateCustomerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CustomerProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerProfileRestMapper {
    CreateCustomerProfileCommand toCreateCustomerProfileCommand(CreateCustomerRequest createCustomerRequest);

    CustomerProfileResponse toCustomerProfileResponse(CustomerProfile profile);
}
