package com.seek_with_sight.application.service.profile;

import com.seek_with_sight.application.port.in.profile.FindSellerProfileByUserIdUseCase;
import com.seek_with_sight.application.port.out.profile.SellerProfileRepositoryPort;
import com.seek_with_sight.domain.model.profile.SellerProfile;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@AllArgsConstructor
@Slf4j
public class FindSellerProfileByUserIdService implements FindSellerProfileByUserIdUseCase {
    private final SellerProfileRepositoryPort repo;

    @Override
    public SellerProfile find(UUID userId) {
        log.info("Find seller profile by user id {}", userId);

        return repo.findByUserId(userId).orElseThrow();
    }
}
