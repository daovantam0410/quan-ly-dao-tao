package com.daovantam.quanlydaotao.security.jwt.util;

public enum JwtClaimKey {
    ID("id"),
    EMAIL("email"),
    USERNAME("userName"),
//    CODE("code"),
    FULLNAME("fullName"),
    ROLE("role");

    private String value;

    JwtClaimKey(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
