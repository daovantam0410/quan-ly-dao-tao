package com.daovantam.quanlydaotao.repository;

import com.daovantam.quanlydaotao.entity.Teacher;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepository extends BaseRepository<Teacher, Long> {

    @Query(value = "SELECT t FROM Teacher t INNER JOIN FETCH t.branch b")
    List<Teacher> findAllTeacher();

    List<Teacher> findAllByBranchId(long id);
}
