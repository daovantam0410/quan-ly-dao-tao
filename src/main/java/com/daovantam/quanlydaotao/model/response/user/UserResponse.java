package com.daovantam.quanlydaotao.model.response.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponse {
    private long id;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private String dateOfBirth;
    private int gender;
    private long roleId;
}
