package com.seek_with_sight.user.application.port.out;

import com.seek_with_sight.user.domain.model.User;

public interface CurrentUserPort {
    User getCurrentUser();
}
