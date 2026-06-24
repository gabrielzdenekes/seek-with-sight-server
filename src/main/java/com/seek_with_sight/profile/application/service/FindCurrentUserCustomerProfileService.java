package com.seek_with_sight.profile.application.service;

import com.seek_with_sight.profile.application.port.in.FindCurrentUserCustomerProfile;
import com.seek_with_sight.profile.application.port.out.CustomerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.CustomerProfile;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class FindCurrentUserCustomerProfileService implements FindCurrentUserCustomerProfile {
    private final CurrentUserPort currentUserPort;
    private final CustomerProfileRepositoryPort repo;

    @Override
    public CustomerProfile find() {
        var user = currentUserPort
                .getCurrentUser()
                .orElseThrow();

        log.info("Find customer profile by email {}", user.getEmail());

        return repo.findByUserEmail(user.getEmail()).orElseThrow();
    }
}
