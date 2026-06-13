package com.seek_with_sight.shared.domain.exception;

import java.util.Arrays;

public class BusinessException extends RuntimeException {
    private final String errorCode;
    private final String localizedMessageCode;
    private final ErrorType errorType;
    private final Object[] args;

    public BusinessException(
            String errorCode,
            String localizedMessageCode,
            ErrorType errorType,
            Object[] args) {
        this.localizedMessageCode = localizedMessageCode;
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.args = args;
    }

    public String getLocalizedMessageCode() {
        return localizedMessageCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public Object[] getArgs() {
        return args;
    }

    @Override
    public String toString() {
        return "BusinessException{" +
                "errorCode='" + getErrorCode() + '\'' +
                ", localizedMessageCode='" + getLocalizedMessageCode() + '\'' +
                ", errorType=" + getErrorType() +
                ", args=" + Arrays.toString(getArgs()) +
                '}';
    }
}
