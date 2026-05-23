package com.seek_with_sight.infrastructure.adapter.in.rest.shared;

import com.seek_with_sight.domain.exception.BusinessException;
import com.seek_with_sight.domain.exception.security.UnauthorizedException;
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
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
    private final LocalizedMessageService messageService;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        var status = HttpStatus.BAD_REQUEST;

        if (ex instanceof UnauthorizedException) {
            log.warn("Unauthorized access: {}", ex.getMessage());
            status = HttpStatus.UNAUTHORIZED;
        } else {
            log.warn("Bussiness rule violation: {}", ex.getMessage());
        }

        var apiResponse = ApiResponse.error(getLocalizedErrorMessage(ex.getLocalizedMessageCode()), status);

        return new ResponseEntity<>(apiResponse, status);
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
        var apiResponse = ApiResponse.error(getLocalizedErrorMessage("validation.failed"), errors);

        return new ResponseEntity<>(apiResponse, status);
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

        var apiResponse = ApiResponse.error(getLocalizedErrorMessage("validation.failed"), details);
        var status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(apiResponse, status);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        var  status = HttpStatus.INTERNAL_SERVER_ERROR;
        var apiResponse = ApiResponse.error(getLocalizedErrorMessage("generic.error"), null);
        log.error("Unhandled exception: method={} path={}",
                request.getMethod(), request.getRequestURI(), ex);

        return new ResponseEntity<>(apiResponse, status);
    }

    private String getLocalizedErrorMessage(String key) {
        var locale = LocaleContextHolder.getLocale();
        return messageService.getMessage(key, locale);
    }
}
