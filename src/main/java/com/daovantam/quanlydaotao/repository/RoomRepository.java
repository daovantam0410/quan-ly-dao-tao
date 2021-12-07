package com.daovantam.quanlydaotao.repository;

import com.daovantam.quanlydaotao.entity.Room;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends BaseRepository<Room, Long>{

    @Query(value = "SELECT r FROM Room r INNER JOIN FETCH r.branch b")
    List<Room> findAllRoom();
}
