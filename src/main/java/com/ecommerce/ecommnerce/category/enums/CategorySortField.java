package com.ecommerce.ecommnerce.category.enums;

public enum CategorySortField {

    ID("id"),
    TITLE("title"),
    PARENTCATEGORYTITLE("parentCategoryTitle"),
    ROOTPARENTCATEGORY("rootParentCategory"),
    STATUS("status");


    private String value;

    private CategorySortField(String value){
        this.value=value;
    }


    public String getValue() {
        return value;
    }

}
