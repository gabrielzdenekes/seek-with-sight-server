package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.FindSellerProfileByEmailUseCase;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateSellerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.SellerProfileResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper.SellerProfileRestMapper;
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
@RequestMapping("/api/seller-profile")
public class SellerProfileController {
    private final SellerProfileRestMapper mapper;
    private final CreateSellerProfileUseCase createSellerProfileUseCase;
    private final FindSellerProfileByEmailUseCase findSellerProfileByEmailUseCase;

    @PostMapping
    public User createSellerProfile(@Valid @RequestBody CreateSellerRequest createSellerRequest) {
        var createSellerCommand = mapper.toCreateSellerProfileCommand(createSellerRequest);
        return createSellerProfileUseCase.createSellerProfile(createSellerCommand);
    }

    @GetMapping("/me")
    public SellerProfileResponse getById(Authentication authentication) {
        var email = authentication.getName();
        var profile = findSellerProfileByEmailUseCase.find(email);

        return mapper.toSellerProfileResponse(profile);
    }
}
