package com.seek_with_sight.domain.exception;

public class BusinessException extends RuntimeException {
    private final String localizedMessageCode;

    public BusinessException(String message, String localizedMessageCode) {
        super(message);
        this.localizedMessageCode = localizedMessageCode;
    }

    public String getLocalizedMessageCode() {
        return localizedMessageCode;
    }
}
