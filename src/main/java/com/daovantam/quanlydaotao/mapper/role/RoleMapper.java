package com.daovantam.quanlydaotao.mapper.role;
import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.model.response.role.RoleResponse;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public RoleResponse mapToResponse(Role role){
        RoleResponse roleResponse = new RoleResponse();
        BeanUtils.refine(role, roleResponse, BeanUtils::copyNonNull);

        return roleResponse;
    }
}
