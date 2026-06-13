package com.seek_with_sight.profile.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.profile.application.port.in.command.CreateCustomerProfileCommand;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CreateCustomerRequest;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CustomerProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerProfileRestMapper {
    CreateCustomerProfileCommand toCreateCustomerProfileCommand(CreateCustomerRequest createCustomerRequest);

    CustomerProfileResponse toCustomerProfileResponse(CustomerProfile profile);
}
