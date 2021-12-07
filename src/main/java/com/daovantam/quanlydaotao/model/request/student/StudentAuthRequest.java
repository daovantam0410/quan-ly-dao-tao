package com.daovantam.quanlydaotao.model.request.student;

import lombok.Data;

@Data
public class StudentAuthRequest {
    private String code;
    private String password;
}
