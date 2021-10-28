package com.daovantam.quanlydaotao.controller.student;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.student.StudentMapper;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.service.StudentService;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-student")
@RestController
@RequestMapping("/admin")
@Data
public class StudentController extends BaseController {
    private final StudentService studentService;
    private final StudentMapper studentMapper;

    /*@GetMapping("/students")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(studentService.getAll(pageRequest,studentMapper::mapToResponse));
    }*/

    /*@GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.findAll());
    }*/

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponse>> findAll(){
        return ResponseEntity.ok(studentService.fetchStudentDataJoinTable());
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(studentService.findById(id, studentMapper::mapToResponse));
    }

}
