package com.ecommerce.ecommnerce.common.enums;

public enum ExceptionMessage {
    CATEGORY_NOT_FOUND("Category Not Found");

    private String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
