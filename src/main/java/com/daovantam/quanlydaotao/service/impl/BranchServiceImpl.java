package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.repository.BranchRepository;
import com.daovantam.quanlydaotao.service.BranchService;
import org.springframework.stereotype.Service;

@Service
public class BranchServiceImpl extends AbstractServiceImpl<Branch, Long> implements BranchService {

    public BranchServiceImpl(BranchRepository branchRepository){
        super(branchRepository);
    }
}
