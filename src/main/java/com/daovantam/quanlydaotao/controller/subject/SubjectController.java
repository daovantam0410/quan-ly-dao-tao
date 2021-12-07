package com.daovantam.quanlydaotao.controller.subject;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.subject.SubjectMapper;
import com.daovantam.quanlydaotao.model.request.subject.SubjectRequest;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.model.response.subject.SubjectResponse;
import com.daovantam.quanlydaotao.service.SubjectService;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Data
public class SubjectController extends BaseController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    /*@GetMapping("/subjects")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(subjectService.getAll(pageRequest, subjectMapper::mapToResponse));
    }*/

    @GetMapping("/subjects")
    public ResponseEntity<List<SubjectResponse>> findAll(){
        return ResponseEntity.ok(subjectService.findAll(subjectMapper::mapToResponse));
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<SubjectResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(subjectService.findById(id, subjectMapper::mapToResponse));
    }

    @PostMapping("/subject")
    public ResponseEntity<Void> insert(@RequestBody SubjectRequest subjectRequest){
        subjectService.save(subjectRequest, subjectMapper::mapToEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/subject/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody SubjectRequest subjectRequest){
        subjectRequest.setId(id);
        subjectService.save(subjectRequest, subjectMapper::mapToEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/subject/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        subjectService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
