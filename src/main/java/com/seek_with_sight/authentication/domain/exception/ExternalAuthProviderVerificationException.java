package com.seek_with_sight.authentication.domain.exception;

import com.seek_with_sight.shared.domain.exception.BusinessException;
import com.seek_with_sight.shared.domain.exception.ErrorType;

public class ExternalAuthProviderVerificationException extends BusinessException {

    public ExternalAuthProviderVerificationException(String providerName) {
        super(
                "EXTERNAL_AUTH_PROVIDER_VERIFICATION",
                ErrorType.UNAUTHORIZED,
                "Verification for provider %s failed",
                providerName
        );
    }

    public ExternalAuthProviderVerificationException(Throwable cause, String providerName) {
        super(
                cause,
                "EXTERNAL_AUTH_PROVIDER_VERIFICATION",
                ErrorType.UNAUTHORIZED,
                "Verification for provider %s failed",
                providerName
        );
    }
}
