package com.seek_with_sight.profile.infrastructure.adapter.in.rest;

import com.seek_with_sight.profile.application.port.in.CreateSellerProfileUseCase;
import com.seek_with_sight.profile.application.port.in.FindSellerProfileByEmailUseCase;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.CreateSellerRequest;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.dto.SellerProfileResponse;
import com.seek_with_sight.profile.infrastructure.adapter.in.rest.mapper.SellerProfileRestMapper;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.dto.UserResponse;
import com.seek_with_sight.user.infrastructure.adapter.in.rest.mapper.UserRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/seller-profiles")
public class SellerProfilesController {
    private final SellerProfileRestMapper sellerMapper;
    private final CreateSellerProfileUseCase createSellerProfileUseCase;
    private final FindSellerProfileByEmailUseCase findSellerProfileByEmailUseCase;
    private final UserRestMapper userRestMapper;

    @PostMapping
    public UserResponse createSellerProfile(@Valid @RequestBody CreateSellerRequest createSellerRequest) {
        var createSellerCommand = sellerMapper.toCreateSellerProfileCommand(createSellerRequest);
        var createdUser = createSellerProfileUseCase.createSellerProfile(createSellerCommand);

        return userRestMapper.toResponse(createdUser);
    }

    @GetMapping("/me")
    public SellerProfileResponse getById(Authentication authentication) {
        var email = authentication.getName();
        var profile = findSellerProfileByEmailUseCase.find(email);

        return sellerMapper.toSellerProfileResponse(profile);
    }
}
