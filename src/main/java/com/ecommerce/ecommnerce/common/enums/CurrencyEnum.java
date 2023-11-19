package com.ecommerce.ecommnerce.common.enums;

public enum CurrencyEnum {
    INDIAN("INR");

    private String value;

    CurrencyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
