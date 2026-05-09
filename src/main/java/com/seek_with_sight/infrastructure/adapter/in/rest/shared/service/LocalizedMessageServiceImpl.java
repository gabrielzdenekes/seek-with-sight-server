package com.seek_with_sight.infrastructure.adapter.in.rest.shared.service;

import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class LocalizedMessageServiceImpl implements LocalizedMessageService {
    private final MessageSource messageSource;

    public String getMessage(String messageCode, Locale locale) {
        return this.messageSource.getMessage(messageCode, null, locale);
    }
}
