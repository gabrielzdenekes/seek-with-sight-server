package com.seek_with_sight.infrastructure.adapter.in.rest.shared;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.ErrorType;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ApiErrorResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ApiResponse;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.dto.ValidationErrorDto;
import com.seek_with_sight.infrastructure.adapter.in.rest.shared.service.base.LocalizedMessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final LocalizedMessageService messageService;

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException ex) {
        var errorResponse = ApiErrorResponse.create(
                getLocalizedErrorMessage("auth.error.unauthorized"),
                null,
                ErrorType.UNAUTHORIZED.name(),
                HttpStatus.UNAUTHORIZED
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        var status = mapErrorTypeToHttpStatus(ex.getErrorType());

        log.warn(ex.toString());

        var errorResponse = ApiErrorResponse.create(
                getLocalizedErrorMessage(ex.getLocalizedMessageCode()),
                null,
                ex.getErrorCode(),
                status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<ValidationErrorDto[]>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        var errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ValidationErrorDto(fe.getField(), fe.getDefaultMessage()))
                .toArray(ValidationErrorDto[]::new);

        log.warn("Validation failed: fields={}", Arrays.toString(errors));

        var status = HttpStatus.BAD_REQUEST;
        var errorResponse = ApiErrorResponse.create(
                getLocalizedErrorMessage("validation.failed"),
                errors,
                ErrorType.VALIDATION.name(),
                status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleConstraintViolation(
            ConstraintViolationException ex,
            HttpServletRequest request
    ) {
        var details = ex.getConstraintViolations()
                .stream()
                .map(fe -> new ValidationErrorDto(fe.getPropertyPath().toString(), fe.getMessage()))
                .toArray(ValidationErrorDto[]::new);

        log.warn("Constraint violation failed: constraints={}", Arrays.toString(details));

        var status = HttpStatus.BAD_REQUEST;
        var errorResponse = ApiErrorResponse.create(
                getLocalizedErrorMessage("validation.failed"),
                details,
                ErrorType.VALIDATION.name(),
                status
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var errorResponse = ApiErrorResponse.create(
                getLocalizedErrorMessage("generic.error"),
                null,
                ErrorType.INTERNAL.name(),
                status
        );

        log.error("Unhandled exception: method={} path={}",
                request.getMethod(), request.getRequestURI(), ex);

        return new ResponseEntity<>(errorResponse, status);
    }

    private String getLocalizedErrorMessage(String key) {
        var locale = LocaleContextHolder.getLocale();
        return messageService.getMessage(key, locale);
    }

    private HttpStatus mapErrorTypeToHttpStatus(ErrorType errorType) {
        return switch (errorType) {
            case VALIDATION -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case BUSINESS_RULE -> HttpStatus.UNPROCESSABLE_CONTENT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
