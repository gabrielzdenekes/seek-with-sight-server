package com.seek_with_sight.application.service.email;

import com.seek_with_sight.domain.port.in.email.VerifyEmailUseCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class VerifyEmailService implements VerifyEmailUseCase {
}
