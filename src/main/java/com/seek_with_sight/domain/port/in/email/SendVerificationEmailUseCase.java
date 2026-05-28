package com.seek_with_sight.domain.port.in.email;

import com.seek_with_sight.domain.model.user.User;

public interface SendVerificationEmailUseCase {
    void sendVerificationEmail(User user);
}
