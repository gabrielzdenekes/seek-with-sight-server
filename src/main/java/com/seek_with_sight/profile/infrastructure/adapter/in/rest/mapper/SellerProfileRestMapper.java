package com.seek_with_sight.profile.infrastructure.adapter.in.rest.mapper;

import com.seek_with_sight.profile.application.port.in.command.CreateSellerProfileCommand;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CreateSellerRequest;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.SellerProfileResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SellerProfileRestMapper {
    CreateSellerProfileCommand toCreateSellerProfileCommand(CreateSellerRequest createSellerRequest);

    SellerProfileResponse toSellerProfileResponse(SellerProfile profile);
}
