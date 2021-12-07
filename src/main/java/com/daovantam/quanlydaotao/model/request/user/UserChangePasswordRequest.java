package com.daovantam.quanlydaotao.model.request.user;

import lombok.Data;

@Data
public class UserChangePasswordRequest {
    private long id;
    private String userName;
    private String oldPassword;
    private String newPassword;
}
