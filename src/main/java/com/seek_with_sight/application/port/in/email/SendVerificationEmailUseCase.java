package com.seek_with_sight.application.port.in.email;

import com.seek_with_sight.user.domain.model.User;

public interface SendVerificationEmailUseCase {
    void sendVerificationEmail(User user);
}
