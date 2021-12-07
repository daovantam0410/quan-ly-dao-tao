package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TeacherService extends BaseService<Teacher, Long> {
    List<TeacherResponse> findAll();

    List<TeacherResponse> findAllTeacher();

    List<TeacherResponse> findAllTeacherByBranchId(long id);
}
