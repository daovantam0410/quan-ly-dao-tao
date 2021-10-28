package com.daovantam.quanlydaotao.model.request.student;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class StudentRequest {
    private long id;
    private String code;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private LocalDate dateOfBirth;
    private String time;
    private long branchId;
    private long genderId;
    private long roleId;
    private long roomId;
    private List<Long> subjectIds;
}
