package com.seek_with_sight.infrastructure.adapter.in.rest.profile;

import com.seek_with_sight.domain.port.in.profile.CreateSellerProfileUseCase;
import com.seek_with_sight.domain.port.in.profile.command.CreateCustomerProfileCommand;
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
    private final CreateSellerProfileUseCase createSellerProfileUseCase;

    @PostMapping
    public void createCustomerProfile(@Valid @RequestBody CreateCustomerProfileCommand createCustomerProfileCommand) {

    }
}
