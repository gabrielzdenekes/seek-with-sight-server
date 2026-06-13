package com.seek_with_sight.profile.application.service;

import com.seek_with_sight.profile.application.port.in.FindSellerProfileByEmailUseCase;
import com.seek_with_sight.profile.application.port.out.SellerProfileRepositoryPort;
import com.seek_with_sight.profile.domain.model.SellerProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public class FindSellerProfileByEmailService implements FindSellerProfileByEmailUseCase {
    private final SellerProfileRepositoryPort repo;

    @Override
    public SellerProfile find(String email) {
        log.info("Find seller profile by email {}", email);

        return repo.findByUserEmail(email).orElseThrow();
    }
}
