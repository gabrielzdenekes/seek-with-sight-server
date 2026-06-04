package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindCustomerProfileByUserIdUseCase;
import com.seek_with_sight.application.port.out.profile.CustomerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.CustomerProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class FindCustomerProfileByUserIdService implements FindCustomerProfileByUserIdUseCase {
    private final CustomerProfileRepositoryPort repo;

    @Override
    public CustomerProfile find(UUID userId) {
        log.info("Find customer profile by user id {}", userId);

        return repo.findByUserId(userId)
                .orElseThrow();
    }
}
