package com.daovantam.quanlydaotao.controller.subject;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.subject.SubjectMapper;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.service.SubjectService;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@Data
public class SubjectController extends BaseController {
    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;

    @GetMapping("/subjects")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(subjectService.getAll(pageRequest, subjectMapper::mapToResponse));
    }
}
