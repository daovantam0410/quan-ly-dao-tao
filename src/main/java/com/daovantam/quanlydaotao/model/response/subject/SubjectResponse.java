package com.daovantam.quanlydaotao.model.response.subject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubjectResponse {
    private long id;
    private String code;
    private String name;
    private long studentId;
    private long teacherId;
    private String teacherCode;
}
