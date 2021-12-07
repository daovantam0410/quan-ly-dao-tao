package com.daovantam.quanlydaotao.controller.branch;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.branch.BranchMapper;
import com.daovantam.quanlydaotao.model.request.branch.BranchFilterRequest;
import com.daovantam.quanlydaotao.model.request.branch.BranchRequest;
import com.daovantam.quanlydaotao.model.response.branch.BranchResponse;
import com.daovantam.quanlydaotao.repository.specification.BranchSpecification;
import com.daovantam.quanlydaotao.service.BranchService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-branchs")
@RestController
@RequestMapping("/admin")
@Data
public class BranchController extends BaseController {
    private final BranchService branchService;
    private final BranchMapper branchMapper;

    /*@GetMapping("/branchs")
    public ResponseEntity<BaseResponse> findAll(@RequestParam int index, @RequestParam int size){
        PageRequest pageRequest = PageRequest.of(index, size);

        return success(branchService.getAll(pageRequest, branchMapper::mapToResponse));
    }*/

    @GetMapping("/branchs")
    public ResponseEntity<List<BranchResponse>> findAllBranch(){
        return ResponseEntity.ok(branchService.findAll(branchMapper::mapToResponse));
    }

    @GetMapping("/branch")
    public ResponseEntity<List<BranchResponse>> filter(@ModelAttribute BranchFilterRequest filterRequest){
        return ResponseEntity.ok(branchService.filter(BranchSpecification.filter(filterRequest), branchMapper::mapToResponse));
    }

    /*@GetMapping("/branch/{id}")
    public ResponseEntity<BaseResponse> findById(@PathVariable long id){
        return success(branchService.findById(id, branchMapper::mapToResponse));
    }*/

    @PostMapping("/branch")
    public ResponseEntity<Void> insert(@RequestBody BranchRequest branchRequest){
        branchService.save(branchRequest, branchMapper::mapToEntity);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/branch/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody BranchRequest branchRequest){
        branchRequest.setId(id);
        branchService.save(branchRequest, branchMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/branch/{id}")
    public ResponseEntity<BranchResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(branchService.findById(id, branchMapper::mapToResponse));
    }

    @DeleteMapping("/branch/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        branchService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
