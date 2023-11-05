package com.ecommerce.ecommnerce.common.enums;

public enum DateFormat {
    CLIENT_FORMAT("dd-MM-yyyy HH:mm:ss");

    private String value;

    DateFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
