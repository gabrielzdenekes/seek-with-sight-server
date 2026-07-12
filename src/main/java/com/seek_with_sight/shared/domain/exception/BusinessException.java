package com.seek_with_sight.shared.domain.exception;

import java.util.Arrays;

public class BusinessException extends RuntimeException {
    private String errorCode;
    private ErrorType errorType;
    private Object[] args;

    public BusinessException(String errorCode, ErrorType errorType, String message, Object... args) {
        super(String.format(message, args));
        setFields(errorCode, errorType, args);
    }

    public BusinessException(Throwable cause, String errorCode, ErrorType errorType, String message, Object... args) {
        super(String.format(message, args), cause);
        setFields(errorCode, errorType, args);
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
                ", errorType=" + getErrorType() +
                ", args=" + Arrays.toString(getArgs()) +
                '}';
    }

    private void setFields(String errorCode, ErrorType errorType, Object... args) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.args = args != null ? args.clone() : new Object[0];
    }
}
