package com.daovantam.quanlydaotao.mapper.teacher;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.entity.Gender;
import com.daovantam.quanlydaotao.entity.Role;
import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.model.response.teacher.TeacherResponse;
import com.daovantam.quanlydaotao.repository.BranchRepository;
import com.daovantam.quanlydaotao.repository.GenderRepository;
import com.daovantam.quanlydaotao.repository.RoleRepository;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class TeacherMapper {

    private final BranchRepository branchRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;

    public TeacherResponse mapToResponse(Teacher teacher){
        TeacherResponse teacherResponse = new TeacherResponse();
        BeanUtils.refine(teacher, teacherResponse, BeanUtils::copyNonNull);

        List<Branch> branches = branchRepository.findAll();
        List<Gender> genders = genderRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (Branch branch:branches) {
            if (branch.getId() == teacher.getBranch().getId()){
                teacherResponse.setBranchId(branch.getId());
            }
        }

        for (Gender gender:genders) {
            if (gender.getId() == teacher.getGender().getId()){
                teacherResponse.setGenderId(gender.getId());
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
