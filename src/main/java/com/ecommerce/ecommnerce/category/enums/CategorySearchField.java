package com.ecommerce.ecommnerce.category.enums;

public enum CategorySearchField {

    TITLE("title"),
    ID("id"),
    PARENTCATEGORYID("parentCategoryId"),
    PARENTCATEGORYTITLE("parentCategoryTitle"),
    ROOTPARENTCATEGORY("rootParentCategory"),
    STATUS("status");


    private String value;

    private CategorySearchField(String value){
        this.value=value;
    }


    public String getValue() {
        return value;
    }

}
