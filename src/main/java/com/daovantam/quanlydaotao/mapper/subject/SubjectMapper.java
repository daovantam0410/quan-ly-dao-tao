package com.daovantam.quanlydaotao.mapper.subject;

import com.daovantam.quanlydaotao.entity.Student;
import com.daovantam.quanlydaotao.entity.Subject;
import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.model.request.subject.SubjectRequest;
import com.daovantam.quanlydaotao.model.response.subject.SubjectResponse;
import com.daovantam.quanlydaotao.repository.StudentRepository;
import com.daovantam.quanlydaotao.repository.TeacherRepository;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class SubjectMapper {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public Subject mapToEntity(SubjectRequest subjectRequest){
        Subject subject = new Subject();
        BeanUtils.refine(subjectRequest, subject, BeanUtils::copyNonNull);

        List<Student> students = studentRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        for (Student student:students) {
            if (student.getId() == subjectRequest.getStudentId()){
                subject.setStudent(student);
            }
        }

        for (Teacher teacher:teachers) {
            if (teacher.getId() == subjectRequest.getTeacherId()){
                subject.setTeacher(teacher);
            }
        }

        return subject;
    }

    public SubjectResponse mapToResponse(Subject subject){
        SubjectResponse subjectResponse = new SubjectResponse();
        BeanUtils.refine(subject, subjectResponse, BeanUtils::copyNonNull);

        List<Student> students = studentRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        for (Student student:students) {
            if (student.getId()==subject.getStudent().getId()){
                subjectResponse.setStudentId(student.getId());
            }
        }

        for (Teacher teacher:teachers) {
            if (teacher.getId()==subject.getTeacher().getId()){
                subjectResponse.setTeacherId(teacher.getId());
                subjectResponse.setTeacherCode(teacher.getCode());
            }
        }

        return subjectResponse;
    }
}
