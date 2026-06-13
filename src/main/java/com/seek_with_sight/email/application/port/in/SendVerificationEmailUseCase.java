package com.seek_with_sight.email.application.port.in;

import com.seek_with_sight.user.domain.model.User;

public interface SendVerificationEmailUseCase {
    void sendVerificationEmail(User user);
}
