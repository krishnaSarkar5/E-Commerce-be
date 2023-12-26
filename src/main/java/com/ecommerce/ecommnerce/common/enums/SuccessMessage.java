package com.ecommerce.ecommnerce.common.enums;

public enum SuccessMessage {
    ADDRESS_ADD("Address added successfully"),

    ATTRIBUTE_UPDATED("Attribute updated successfully"),

    DATE_DELETED("Data deleted successfully");


    private String value;

    SuccessMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
