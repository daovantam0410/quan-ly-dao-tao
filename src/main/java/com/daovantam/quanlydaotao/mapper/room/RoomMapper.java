package com.daovantam.quanlydaotao.mapper.room;

import com.daovantam.quanlydaotao.entity.Branch;
import com.daovantam.quanlydaotao.entity.Room;
import com.daovantam.quanlydaotao.entity.Teacher;
import com.daovantam.quanlydaotao.model.request.room.RoomRequest;
import com.daovantam.quanlydaotao.model.response.room.RoomResponse;
import com.daovantam.quanlydaotao.repository.BranchRepository;
import com.daovantam.quanlydaotao.repository.TeacherRepository;
import com.daovantam.quanlydaotao.utils.BeanUtils;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class RoomMapper {

    private final BranchRepository branchRepository;
    private final TeacherRepository teacherRepository;

    public Room mapToEntity(RoomRequest roomRequest){
        Room room = new Room();
        BeanUtils.refine(roomRequest, room, BeanUtils::copyNonNull);

        List<Branch> branches = branchRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        for (Branch branch:branches) {
            if (branch.getId() == roomRequest.getBranchId()){
                room.setBranch(branch);
            }
        }

        for (Teacher teacher:teachers) {
            if (teacher.getId() == roomRequest.getTeacherId()){
                room.setTeacher(teacher);
            }
        }

        return room;
    }

    public RoomResponse mapToResponse(Room room){
        RoomResponse roomResponse = new RoomResponse();
        BeanUtils.refine(room, roomResponse, BeanUtils::copyNonNull);

        List<Branch> branches = branchRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();

        for (Branch branch:branches) {
            if (branch.getId() == room.getBranch().getId()){
                roomResponse.setBranchName(branch.getName());
                roomResponse.setBranchId(branch.getId());
            }
        }

        for (Teacher teacher:teachers) {
            if (teacher.getId() == room.getTeacher().getId()){
                roomResponse.setTeacherId(teacher.getId());
            }
        }

        return roomResponse;
    }
}
