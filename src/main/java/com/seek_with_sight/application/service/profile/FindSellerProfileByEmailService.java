package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindSellerProfileByEmailUseCase;
import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.SellerProfile;
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
