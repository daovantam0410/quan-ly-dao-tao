package com.daovantam.quanlydaotao.model.response.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private long id;
    private String code;
    private String fullName;
    private String password;
    private String email;
    private String phone;
    private String address;
    private int gender;
    private String dateOfBirth;
    private String time;
    private String branchName;
    private String roomName;
    private String photos;
    private long branchId;
    private long roomId;
    private long roleId;
}
