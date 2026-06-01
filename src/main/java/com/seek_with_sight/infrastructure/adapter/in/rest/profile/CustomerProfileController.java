package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.application.port.in.profile.CreateCustomerProfileUseCase;
import com.seek_with_sight.domain.model.user.User;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.dto.CreateCustomerRequest;
import com.seek_with_sight.infrastructure.adapter.in.rest.profile.mapper.CustomerProfileRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer-profile")
public class CustomerProfileController {
    private final CustomerProfileRestMapper mapper;
    private final CreateCustomerProfileUseCase createCustomerProfileUseCase;

    @PostMapping
    public User createCustomerProfile(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        var command = mapper.toCreateCustomerProfileCommand(createCustomerRequest);
        return createCustomerProfileUseCase.createCustomerProfile(command);
    }
}
