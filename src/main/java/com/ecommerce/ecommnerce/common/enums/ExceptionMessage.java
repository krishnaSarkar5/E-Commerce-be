package com.ecommerce.ecommnerce.common.enums;

public enum ExceptionMessage {
    CATEGORY_NOT_FOUND("Category Not Found"),
    INVALID_ATTRIBUTE("Invalid attribute"),
    INVALID_CURRENCY("Invalid Currency");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
