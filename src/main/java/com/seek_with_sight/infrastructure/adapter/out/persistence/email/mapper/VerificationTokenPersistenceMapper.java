package com.seek_with_sight.infrastructure.adapter.out.persistence.email.mapper;

import com.seek_with_sight.domain.model.email.EmailVerificationToken;
import com.seek_with_sight.infrastructure.adapter.out.persistence.email.entity.EmailVerificationTokenEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VerificationTokenPersistenceMapper {
    EmailVerificationTokenEntity fromDomain(EmailVerificationToken token);

    EmailVerificationToken fromEntity(EmailVerificationTokenEntity token);
}
