package com.ecommerce.ecommnerce.common.enums;

public enum Status {

    DELETE((byte)0),
    ACTIVE((byte)1),
    INACTIVE((byte)2);

    private Byte value;

     Status(Byte value){
        this.value = value;
    }

    public Byte getValue(){
        return this.value;
    }
}
