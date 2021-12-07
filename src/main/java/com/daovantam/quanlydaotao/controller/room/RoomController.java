package com.daovantam.quanlydaotao.controller.room;

import com.daovantam.quanlydaotao.controller.BaseController;
import com.daovantam.quanlydaotao.mapper.room.RoomMapper;
import com.daovantam.quanlydaotao.model.request.room.RoomRequest;
import com.daovantam.quanlydaotao.model.response.room.RoomResponse;
import com.daovantam.quanlydaotao.service.RoomService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/admin/manager-class")
@RestController
@RequestMapping("/admin")
@Data
public class RoomController extends BaseController {

    private final RoomService roomService;
    private final RoomMapper roomMapper;

    @GetMapping("/rooms")
    public ResponseEntity<List<RoomResponse>> findAll(){
        return ResponseEntity.ok(roomService.findAll(roomMapper::mapToResponse));
    }

    @PostMapping("/room")
    public ResponseEntity<Void> insert(@RequestBody RoomRequest roomRequest){
        roomService.save(roomRequest, roomMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody RoomRequest roomRequest){
        roomRequest.setId(id);
        roomService.save(roomRequest, roomMapper::mapToEntity);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<RoomResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(roomService.findById(id,roomMapper::mapToResponse));
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        roomService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
