package com.seek_with_sight.shared.domain.exception;

import java.util.Arrays;

public class BusinessException extends RuntimeException {
    private ErrorCode errorCode;
    private ErrorType errorType;
    private Object[] args;

    public BusinessException(ErrorCode errorCode, ErrorType errorType, String message, Object... args) {
        super(String.format(message, args));
        setFields(errorCode, errorType, args);
    }

    public BusinessException(
            Throwable cause,
            ErrorCode errorCode,
            ErrorType errorType,
            String message,
            Object... args
    ) {
        super(String.format(message, args), cause);
        setFields(errorCode, errorType, args);
    }

    public ErrorCode getErrorCode() {
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

    private void setFields(ErrorCode errorCode, ErrorType errorType, Object... args) {
        this.errorCode = errorCode;
        this.errorType = errorType;
        this.args = args != null ? args.clone() : new Object[0];
    }
}
