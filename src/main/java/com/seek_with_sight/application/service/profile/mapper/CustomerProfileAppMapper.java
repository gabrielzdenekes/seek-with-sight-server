package com.seek_with_sight.application.service.profile.mapper;

import com.seek_with_sight.application.port.in.profile.command.CreateCustomerProfileCommand;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerProfileAppMapper {
    CustomerProfile fromCreateCustomerProfileCommand(CreateCustomerProfileCommand command);
}
