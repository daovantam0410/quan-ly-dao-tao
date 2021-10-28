package com.daovantam.quanlydaotao.mapper.branch;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.model.response.branch.BranchResponse;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class BranchMapper {

    public BranchResponse mapToResponse(Branch branch){
        BranchResponse branchResponse = new BranchResponse();
        BeanUtils.refine(branch, branchResponse, BeanUtils::copyNonNull);

        return branchResponse;
    }
}
