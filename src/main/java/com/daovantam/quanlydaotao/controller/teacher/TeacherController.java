package com.daovantam.quanlydaotao.controller.teacher;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.teacher.TeacherMapper;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.request.teacher.TeacherRequest;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import com.daovantam.quanlydaotao.service.TeacherService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return ResponseEntity.ok(teacherService.findAll(teacherMapper::mapToResponse));
    }

    /*@GetMapping("/teacher/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(teacherService.findById(id, teacherMapper::mapToResponse));
    }*/

    @GetMapping("/teacher/{id}")
    public ResponseEntity<TeacherResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(teacherService.findById(id, teacherMapper::mapToResponse));
    }

    @PostMapping("/teacher")
    public ResponseEntity<Void> insert(@RequestBody TeacherRequest teacherRequest){
        teacherService.save(teacherRequest, teacherMapper::mapToEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/teacher/{id}")
    public ResponseEntity<Void> update(@RequestBody TeacherRequest teacherRequest, @PathVariable long id){
        teacherRequest.setId(id);
        teacherService.save(teacherRequest, teacherMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        teacherService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/teacher")
    public ResponseEntity<List<TeacherResponse>> findByBranchId(@RequestParam Long branchId){
        return ResponseEntity.ok(teacherService.findAllTeacherByBranchId(branchId));
    }
}
