package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateSellerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper.SellerProfileRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller-profile")
public class SellerProfileController {
    private final SellerProfileRestMapper mapper;
    private final CreateSellerProfileUseCase createSellerProfileUseCase;

    @PostMapping
    public void createSellerProfile(@Valid @RequestBody CreateSellerRequest createSellerRequest) {

    }
}
