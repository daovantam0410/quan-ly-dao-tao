package com.daovantam.quanlydaotao.model.request.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequest {
    private long id;
    private String code;
    private String name;
    private long branchId;
    private long teacherId;
}
