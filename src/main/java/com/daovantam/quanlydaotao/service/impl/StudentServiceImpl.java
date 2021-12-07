package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.mapper.student.StudentMapper;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.repository.StudentRepository;
import com.daovantam.quanlydaotao.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.function.Function;
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
    public List<StudentResponse> findAllStudent() {
        List<Student> students = studentRepository.findAllStudent();
        List<StudentResponse> list = students.stream().map(studentMapper::mapToResponse).collect(Collectors.toList());

        return list;
    }

//    @Override
//    public void save(StudentRequest req, Function<StudentRequest, Student> transform, MultipartFile multipartFile) {
//        if (!multipartFile.isEmpty()){
//            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
//
//            //Set image for student
//            req.setPhotos(fileName);
//            Student student = baseRepository.save(req);
//
//        }
//    }
}
