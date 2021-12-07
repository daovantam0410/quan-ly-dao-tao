package com.daovantam.quanlydaotao.model.response.teacher;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class TeacherResponse {
    private long id;
    private String code;
    private String password;
    private String fullName;
    private String email;
    private String phone;
    private String address;
    private int gender;
    private String dateOfBirth;
    private String branchName;
    private long branchId;
    private long roleId;
}
