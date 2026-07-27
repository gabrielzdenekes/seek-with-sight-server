package com.seek_with_sight.shared.infrastructure.adapter.in.rest;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorCode;
import com.seek_with_sight.shared.domain.exception.ErrorType;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiErrorResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ApiResponse;
import com.seek_with_sight.shared.infrastructure.adapter.in.rest.dto.ValidationErrorDto;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {

    @Value("${app.errors.include-stacktrace:false}")
    private boolean includeStacktrace;

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Malformed or missing HTTP request body",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    public ResponseEntity<ApiResponse<?>> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpServletRequest request
    ) {
        var status = HttpStatus.BAD_REQUEST;
        var errorResponse = ApiErrorResponse.create(
                "Malformed or missing request body",
                null,
                ErrorCode.VALIDATION,
                status.value()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(BadCredentialsException.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "401",
            description = "Invalid or missing authentication credentials",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    public ResponseEntity<ApiResponse<?>> handleBadCredentials(BadCredentialsException ex) {
        var errorResponse = ApiErrorResponse.create(
                ex.getMessage(),
                null,
                ErrorCode.UNAUTHORIZED,
                HttpStatus.UNAUTHORIZED.value()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(BusinessException.class)
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Bad request business error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized business error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "403",
                    description = "Forbidden business error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "404",
                    description = "Resource not found business error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "Resource conflict business error",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "422",
                    description = "Unprocessable domain rule violation",
                    content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
            )
    })
    public ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        var status = mapErrorTypeToHttpStatus(ex.getErrorType());

        log.warn(ex.toString());

        var errorResponse = ApiErrorResponse.create(
                ex.getMessage(),
                null,
                ex.getErrorCode(),
                status.value()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "DTO validation error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
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
                "Validation failed",
                errors,
                ErrorCode.VALIDATION,
                status.value()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "400",
            description = "Constraint violation error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
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
                "Validation failed",
                details,
                ErrorCode.VALIDATION,
                status.value()
        );

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ResponseStatusException.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "500",
            description = "Application status exception error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    public ResponseEntity<ApiResponse<?>> handleResponseStatusException(
            ResponseStatusException ex
    ) {
        var status = HttpStatus.resolve(ex.getStatusCode().value());

        var response = ApiErrorResponse.create(
                ex.getMessage(),
                null,
                ErrorCode.INTERNAL,
                status.value()
        );

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(Exception.class)
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "500",
            description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))
    )
    public ResponseEntity<ApiResponse<?>> handleGenericException(
            Exception ex,
            HttpServletRequest request
    ) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        Object data = null;
        var message = "Something went wrong";

        if (includeStacktrace) {
            data = Arrays.stream(ex.getStackTrace())
                    .map(StackTraceElement::toString)
                    .toArray(String[]::new);

            message = ex.getMessage();
        }

        var errorResponse = ApiErrorResponse.create(
                message,
                data,
                ErrorCode.INTERNAL,
                status.value()
        );

        log.error("Unhandled exception: method={} path={}",
                request.getMethod(), request.getRequestURI(), ex);

        return new ResponseEntity<>(errorResponse, status);
    }

    private HttpStatus mapErrorTypeToHttpStatus(ErrorType errorType) {
        return switch (errorType) {
            case VALIDATION -> HttpStatus.BAD_REQUEST;
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case CONFLICT -> HttpStatus.CONFLICT;
            case FORBIDDEN -> HttpStatus.FORBIDDEN;
            case UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;
            case BUSINESS -> HttpStatus.UNPROCESSABLE_CONTENT;
            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}