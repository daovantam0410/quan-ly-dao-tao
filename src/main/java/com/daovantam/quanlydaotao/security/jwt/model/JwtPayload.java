package com.daovantam.quanlydaotao.security.jwt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtPayload {
    private long id;
//    private String code;
    private String userName;
    private String fullName;
    private String email;
    private String role;
}
