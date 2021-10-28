package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.mapper.student.StudentMapper;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.repository.StudentRepository;
import com.daovantam.quanlydaotao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl extends AbstractServiceImpl<Student, Long> implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository){
        super(studentRepository);
    }

    @Override
    public List<StudentResponse> findAll() {
        List<Student> students = baseRepository.findAll();
        List<StudentResponse> list = students.stream().map(studentMapper::mapToResponse).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<StudentResponse> fetchStudentDataJoinTable() {
        List<Student> students = studentRepository.fetchStudentDataJoinTable();
        List<StudentResponse> list = students.stream().map(studentMapper::mapToResponse).collect(Collectors.toList());

        return list;
    }
}
