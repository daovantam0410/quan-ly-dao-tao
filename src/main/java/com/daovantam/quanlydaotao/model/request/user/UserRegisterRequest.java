package com.daovantam.quanlydaotao.model.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterRequest {
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private int gender;
    private String dateOfBirth;
    private long roleId;
}
