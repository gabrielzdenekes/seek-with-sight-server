package com.seek_with_sight.shared.domain.exception;

public enum ErrorCode {
    /*
        Authentication
     */
    EXTERNAL_AUTH_PROVIDER_VERIFICATION,
    UNAUTHORIZED,

    /*
        Cart
     */
    CART_ITEM_NOT_FOUND,
    ITEM_ALREADY_ADDED_TO_CART,

    /*
        Email
     */
    EMAIL_TOKEN_ALREADY_USED,
    EMAIL_TOKEN_EXPIRED,
    EMAIL_TOKEN_NOT_FOUND
}
