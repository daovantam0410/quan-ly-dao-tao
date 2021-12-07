package com.daovantam.quanlydaotao.model.response.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomResponse {
    private long id;
    private String code;
    private String name;
    private String branchName;
    private long branchId;
    private long teacherId;
}
