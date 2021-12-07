package com.daovantam.quanlydaotao.mapper.teacher;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.model.request.teacher.TeacherRequest;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import com.daovantam.quanlydaotao.repository.BranchRepository;
import com.daovantam.quanlydaotao.repository.RoleRepository;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import com.daovantam.quanlydaotao.utils.TimeUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TeacherMapper {

    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository;

    public Teacher mapToEntity(TeacherRequest teacherRequest){
        Teacher teacher = new Teacher();
        BeanUtils.refine(teacherRequest, teacher, BeanUtils::copyNonNull);
        teacher.setDateOfBirth(TimeUtil.convertToLocalDate(teacherRequest.getDateOfBirth()));

        List<Branch> branches = branchRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (Branch branch:branches) {
            if (branch.getId() == teacherRequest.getBranchId()){
                teacher.setBranch(branch);
            }
        }

        for (Role role:roles) {
            if (role.getId() == teacherRequest.getRoleId()){
                teacher.setRole(role);
            }
        }

        return teacher;
    }

    public TeacherResponse mapToResponse(Teacher teacher){
        TeacherResponse teacherResponse = new TeacherResponse();
        BeanUtils.refine(teacher, teacherResponse, BeanUtils::copyNonNull);
        teacherResponse.setDateOfBirth(TimeUtil.convertToString(teacher.getDateOfBirth()));

        List<Branch> branches = branchRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (Branch branch:branches) {
            if (branch.getId() == teacher.getBranch().getId()){
                teacherResponse.setBranchName(branch.getName());
                teacherResponse.setBranchId(branch.getId());
            }
        }

        for (Role role:roles) {
            if (role.getId() == teacher.getRole().getId()){
                teacherResponse.setRoleId(role.getId());
            }
        }

        return teacherResponse;
    }
}
