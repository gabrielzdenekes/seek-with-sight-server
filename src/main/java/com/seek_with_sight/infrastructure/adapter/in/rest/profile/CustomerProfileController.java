package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.application.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.application.port.in.profile.FindCustomerProfileByEmailUseCase;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateCustomerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CustomerProfileResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper.CustomerProfileRestMapper;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.dto.UserResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.user.mapper.UserRestMapper;
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
@RequestMapping("/api/customer-profile")
public class CustomerProfileController {
    private final CustomerProfileRestMapper mapper;
    private final CreateCustomerProfileUseCase createCustomerProfileUseCase;
    private final FindCustomerProfileByEmailUseCase findCustomerProfileByEmailUseCase;
    private final UserRestMapper userRestMapper;

    @PostMapping
    public UserResponse createCustomerProfile(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        var command = mapper.toCreateCustomerProfileCommand(createCustomerRequest);
        var createdUser = createCustomerProfileUseCase.createCustomerProfile(command);

        return userRestMapper.toResponse(createdUser);
    }

    @GetMapping("/me")
    public CustomerProfileResponse getById(Authentication authentication) {
        var email = authentication.getName();
        var profile = findCustomerProfileByEmailUseCase.find(email);

        return mapper.toCustomerProfileResponse(profile);
    }
}
