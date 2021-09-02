package com.utility;

public enum SystemParameterMember {

 ZIPCODE("ZIPCode"),
 BANKCODE("BankCode"),
 WEEKDAY("Weekday");

 SystemParameterMember(String typeName){
        this.TypeName = typeName;

    }

    private final String TypeName;

    public String getTypeName() {
        return this.TypeName;
    }
}