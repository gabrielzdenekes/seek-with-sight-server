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
    EMAIL_TOKEN_NOT_FOUND,

    /*
        Order
     */
    EMPTY_CART,
    ORDER_NOT_FOUND,

    /*
        Product
     */
    INSUFFICIENT_STOCK,
    INVALID_QUANTITY_UPDATE,
    INVENTORY_NOT_FOUND,
    PRODUCT_ALREADY_REVIEWED,
    PRODUCT_NOT_FOUND,
    PRODUCT_VARIANT_NOT_FOUND,

    /*
        USER
     */
    EMAIL_NOT_VERIFIED
}
