package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.mapper.teacher.TeacherMapper;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import com.daovantam.quanlydaotao.repository.TeacherRepository;
import com.daovantam.quanlydaotao.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl extends AbstractServiceImpl<Teacher, Long> implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository){
        super(teacherRepository);
    }

    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = baseRepository.findAll();
        List<TeacherResponse> list = teachers.stream().map(teacherMapper::mapToResponse).collect(Collectors.toList());

        return list;
    }
}
