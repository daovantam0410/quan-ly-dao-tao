package com.daovantam.quanlydaotao.model.response.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {
    private Long id;
    private String code;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private String time;
    private String branchName;
    private String genderName;
    private String roomName;
//    private long branchId;
//    private long genderId;
//    private long roleId;
//    private long roomId;
}
