package com.ecommerce.ecommnerce.product.enums;

public enum AttributeEnum {
    IMAGE("image"),
    SIZE("size"),
    COLOR("color"),
    FABRIC("fabric");


    private String value;

    AttributeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
