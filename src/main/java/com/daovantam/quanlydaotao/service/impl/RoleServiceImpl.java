package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.model.response.role.RoleResponse;
import com.daovantam.quanlydaotao.repository.RoleRepository;
import com.daovantam.quanlydaotao.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends AbstractServiceImpl<Role, Long> implements RoleService {
    public RoleServiceImpl(RoleRepository roleRepository){
        super(roleRepository);
    }
}
