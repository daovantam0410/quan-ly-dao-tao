package com.daovantam.quanlydaotao.mapper.student;

import com.daovantam.quanlydaotao.entity.*;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.repository.*;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class StudentMapper {

    private final BranchRepository branchRepository;
    private final GenderRepository genderRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;

    public StudentResponse mapToResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.refine(student, studentResponse, BeanUtils::copyNonNull);

        List<Branch> branches = branchRepository.findAll();
        List<Gender> genders = genderRepository.findAll();
//        List<Role> roles = roleRepository.findAll();
        List<Room> rooms = roomRepository.findAll();

        for (Branch branch:branches) {
            if (student.getBranch().getId() == branch.getId()){
                studentResponse.setBranchName(branch.getName());
            }
        }
        for (Gender gender:genders) {
            if (student.getGender().getId() == gender.getId()){
                studentResponse.setGenderName(gender.getName());
            }
        }
//        for (Role role:roles) {
//            if (student.getRole().getId()== role.getId()){
//                studentResponse.setRoleId(role.getId());
//            }
//        }
        for (Room room:rooms) {
            if (student.getRoom().getId() == room.getId()){
                studentResponse.setRoomName(room.getName());
            }
        }

        return studentResponse;
    }
}
