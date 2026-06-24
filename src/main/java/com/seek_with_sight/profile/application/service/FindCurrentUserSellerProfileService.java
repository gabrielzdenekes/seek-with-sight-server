package com.seek_with_sight.profile.application.service;

import com.seek_with_sight.profile.application.port.in.FindCurrentUserSellerProfileUseCase;
import com.seek_with_sight.profile.application.port.out.SellerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import com.seek_with_sight.user.application.port.out.CurrentUserPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class FindCurrentUserSellerProfileService implements FindCurrentUserSellerProfileUseCase {
    private final SellerProfileRepositoryPort repo;
    private final CurrentUserPort currentUserPort;

    @Override
    public SellerProfile find() {
        var user = currentUserPort.getCurrentUser();

        log.info("Find seller profile by email {}", user.getEmail());

        return repo.findByUserEmail(user.getEmail()).orElseThrow();
    }
}
