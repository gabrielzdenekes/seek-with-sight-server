package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.application.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.FindSellerProfileByUserIdUseCase;
import com.seek_with_sight.domain.model.profile.SellerProfile;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateSellerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper.SellerProfileRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/seller-profile")
public class SellerProfileController {
    private final SellerProfileRestMapper mapper;
    private final CreateSellerProfileUseCase createSellerProfileUseCase;
    private final FindSellerProfileByUserIdUseCase findSellerProfileByUserIdUseCase;

    @PostMapping
    public User createSellerProfile(@Valid @RequestBody CreateSellerRequest createSellerRequest) {
        var createSellerCommand = mapper.toCreateSellerProfileCommand(createSellerRequest);
        return createSellerProfileUseCase.createSellerProfile(createSellerCommand);
    }

    @GetMapping("/{userId}")
    public SellerProfile getById(@PathVariable UUID userId) {
        return findSellerProfileByUserIdUseCase.find(userId);
    }
}
