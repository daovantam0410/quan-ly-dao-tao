package com.daovantam.quanlydaotao.mapper.student;

import com.daovantam.quanlydaotao.entity.*;
import com.daovantam.quanlydaotao.model.request.student.StudentRequest;
import com.daovantam.quanlydaotao.model.response.student.StudentResponse;
import com.daovantam.quanlydaotao.repository.*;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import com.daovantam.quanlydaotao.utils.TimeUtil;
import com.daovantam.quanlydaotao.validate.StudentValidate;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class StudentMapper {

    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository;
    private final RoomRepository roomRepository;

    public Student mapToEntity(StudentRequest studentRequest){
        StudentValidate.validate(studentRequest);
        Student student = new Student();
        BeanUtils.refine(studentRequest, student, BeanUtils::copyNonNull);
        student.setDateOfBirth(TimeUtil.convertToLocalDate(studentRequest.getDateOfBirth()));

        List<Branch> branches = branchRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<Role> roles = roleRepository.findAll();
        for (Branch branch:branches) {
            if (branch.getId() == studentRequest.getBranchId()){
                student.setBranch(branch);
            }
        }
        for (Role role:roles) {
            if (role.getId() == studentRequest.getRoleId()){
                student.setRole(role);
            }
        }
        for (Room room:rooms) {
            if (room.getId() == studentRequest.getRoomId()){
                student.setRoom(room);
            }
        }

        return student;
    }

    public StudentResponse mapToResponse(Student student){
        StudentResponse studentResponse = new StudentResponse();
        BeanUtils.refine(student, studentResponse, BeanUtils::copyNonNull);
        studentResponse.setDateOfBirth(TimeUtil.convertToString(student.getDateOfBirth()));

        List<Branch> branches = branchRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<Role> roles = roleRepository.findAll();

        for (Branch branch:branches) {
            if (student.getBranch().getId() == branch.getId()){
                studentResponse.setBranchName(branch.getName());
                studentResponse.setBranchId(branch.getId());
            }
        }

        for (Room room:rooms) {
            if (student.getRoom().getId() == room.getId()){
                studentResponse.setRoomName(room.getName());
                studentResponse.setRoomId(room.getId());
            }
        }

        for (Role role:roles) {
            if (student.getRole().getId() == role.getId()){
                studentResponse.setRoleId(role.getId());
            }
        }

        return studentResponse;
    }
}
