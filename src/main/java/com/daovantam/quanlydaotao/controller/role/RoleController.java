package com.daovantam.quanlydaotao.controller.role;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.role.RoleMapper;
import com.daovantam.quanlydaotao.model.response.role.RoleResponse;
import com.daovantam.quanlydaotao.service.RoleService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/*")
@RestController
@RequestMapping("/admin")
@Data
public class RoleController extends BaseController {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> findAll() {
        return ResponseEntity.ok(roleService.findAll(roleMapper::mapToResponse));
    }
}
