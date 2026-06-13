package com.seek_with_sight.shared.infrastructure.adapter.in.rest.service.base;

import java.util.Locale;

public interface LocalizedMessageService {
    String getMessage(String messageCode, Locale locale);
}
