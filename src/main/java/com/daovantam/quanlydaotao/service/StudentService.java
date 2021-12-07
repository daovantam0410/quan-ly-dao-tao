package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Function;

@Service
public interface StudentService extends BaseService<Student, Long> {

    List<StudentResponse> findAllStudent();

//    void save(StudentRequest req, Function<StudentRequest, Student> transform, MultipartFile multipartFile);
}
