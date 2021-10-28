package com.daovantam.quanlydaotao.repository;

import com.daovantam.quanlydaotao.entity.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends BaseRepository<Student, Long> {

    @Query(value = "SELECT st.id as id, st.code as code, st.fullName as fullName, st.email as email, st.phone as phone, st.address as address, st.dateOfBirth as dateOfBirth, st.time as time, b.name as branchName, g.name as genderName, r.name as roomName FROM Student st JOIN st.branch b JOIN st.gender g JOIN st.room r")
    List<Student> fetchStudentDataJoinTable();
}
