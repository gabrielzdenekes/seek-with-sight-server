package com.seek_with_sight.application.service.profile.mapper;

import com.seek_with_sight.application.port.in.profile.command.CreateSellerProfileCommand;
import com.seek_with_sight.domain.model.profile.SellerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfileAppMapper {
    SellerProfile fromCreateSellerProfileCommand(CreateSellerProfileCommand command);
}
