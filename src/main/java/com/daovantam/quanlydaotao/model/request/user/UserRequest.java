package com.daovantam.quanlydaotao.model.request.user;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserRequest {
    private long id;
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
