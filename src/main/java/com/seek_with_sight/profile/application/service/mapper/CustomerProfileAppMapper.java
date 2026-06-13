package com.seek_with_sight.profile.application.service.mapper;

import com.seek_with_sight.profile.application.port.in.command.CreateCustomerProfileCommand;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerProfileAppMapper {
    CustomerProfile fromCreateCustomerProfileCommand(CreateCustomerProfileCommand command);
}
