package com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base;

import java.util.Locale;

public interface LocalizedMessageService {
    String getMessage(String messageCode, Locale locale);
}
