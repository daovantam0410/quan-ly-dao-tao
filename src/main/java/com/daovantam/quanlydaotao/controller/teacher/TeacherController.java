package com.daovantam.quanlydaotao.controller.teacher;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.teacher.TeacherMapper;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import com.daovantam.quanlydaotao.service.TeacherService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-teacher")
@RestController
@RequestMapping("/admin")
@Data
public class TeacherController extends BaseController {

    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    /*@GetMapping("/teachers")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size) {
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(teacherService.getAll(pageRequest, teacherMapper::mapToResponse));
    }*/

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherResponse>> findAll(){
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/teacher/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(teacherService.findById(id, teacherMapper::mapToResponse));
    }
}
