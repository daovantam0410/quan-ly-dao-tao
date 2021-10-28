package com.daovantam.quanlydaotao.controller.branch;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.branch.BranchMapper;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import com.daovantam.quanlydaotao.service.BranchService;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Data
public class BranchController extends BaseController {
    private final BranchService branchService;
    private final BranchMapper branchMapper;

    @GetMapping("/branchs")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(branchService.getAll(pageRequest, branchMapper::mapToResponse));
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(branchService.findById(id, branchMapper::mapToResponse));
    }
}
