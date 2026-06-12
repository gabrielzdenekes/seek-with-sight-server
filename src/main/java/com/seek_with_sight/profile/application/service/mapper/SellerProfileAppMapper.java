package com.seek_with_sight.profile.application.service.mapper;

import com.seek_with_sight.profile.application.port.in.command.CreateSellerProfileCommand;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfileAppMapper {
    SellerProfile fromCreateSellerProfileCommand(CreateSellerProfileCommand command);
}
