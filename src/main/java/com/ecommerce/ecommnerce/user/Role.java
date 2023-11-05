package com.ecommerce.ecommnerce.user;

public enum Role {



    USER("ROLE_USER");

    private String value;


    Role(String value){
        this.value=value;
    }

    public String getValue(){
        return this.value;
    }
}
