package com.seek_with_sight.profile.application.service;

import com.seek_with_sight.profile.application.port.in.FindCustomerProfileByEmailUseCase;
import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class FindCustomerProfileByEmailService implements FindCustomerProfileByEmailUseCase {
    private final CustomerProfileRepositoryPort repo;

    @Override
    public CustomerProfile find(String email) {
        log.info("Find customer profile by email {}", email);

        return repo.findByUserEmail(email).orElseThrow();
    }
}
