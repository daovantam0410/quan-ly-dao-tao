package com.daovantam.quanlydaotao.model.request.subject;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class SubjectRequest {
    private long id;
    private String code;
    private String name;
    private long studentId;
    private long teacherId;
}
