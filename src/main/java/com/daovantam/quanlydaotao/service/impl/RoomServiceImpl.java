package com.daovantam.quanlydaotao.service.impl;

import com.daovantam.quanlydaotao.entity.Room;
import com.daovantam.quanlydaotao.mapper.room.RoomMapper;
import com.daovantam.quanlydaotao.model.response.room.RoomResponse;
import com.daovantam.quanlydaotao.repository.RoomRepository;
import com.daovantam.quanlydaotao.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl extends AbstractServiceImpl<Room, Long> implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    public RoomServiceImpl(RoomRepository roomRepository){
        super(roomRepository);
    }

    @Override
    public List<RoomResponse> findAllRoom() {
        List<Room> list = roomRepository.findAllRoom();
        List<RoomResponse> roomResponses = list.stream().map(roomMapper::mapToResponse).collect(Collectors.toList());

        return roomResponses;
    }
}
