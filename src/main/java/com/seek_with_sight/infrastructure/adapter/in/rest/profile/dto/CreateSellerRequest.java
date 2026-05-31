package com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto;

import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateSellerRequest extends UserRequest {
    private String businessName;
    private String businessAddress;
    private String taxId;
}
