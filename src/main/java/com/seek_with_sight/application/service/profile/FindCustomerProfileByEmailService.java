package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindCustomerProfileByEmailUseCase;
import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
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
