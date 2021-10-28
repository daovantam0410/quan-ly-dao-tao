package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentService extends BaseService<Student, Long> {
    List<StudentResponse> findAll();

    List<StudentResponse> fetchStudentDataJoinTable();
}
