package com.daovantam.quanlydaotao.service;

import com.daovantam.quanlydaotao.entity.Room;
import com.daovantam.quanlydaotao.model.response.room.RoomResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoomService extends BaseService<Room, Long>{

    List<RoomResponse> findAllRoom();
}
