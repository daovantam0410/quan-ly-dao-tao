package com.daovantam.quanlydaotao.repository;

import com.daovantam.quanlydaotao.entity.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student, Long> {

    @Query(value = "SELECT st FROM Student st INNER JOIN FETCH st.branch b INNER JOIN FETCH st.room r")
    List<Student> findAllStudent();

}
