package com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper;

import com.seek_with_sight.application.port.in.profile.command.CreateSellerProfileCommand;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateSellerRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfileRestMapper {
    CreateSellerProfileCommand toCreateSellerProfileCommand(CreateSellerRequest createSellerRequest);
}
