package com.daovantam.quanlydaotao.exception;

import org.springframework.http.HttpStatus;

public interface NotiTemplate {
    ResponseStatus SUCCESS = new ResponseStatus("SUCCESS", HttpStatus.OK);
    ResponseStatus USER_NOT_FOUND = new ResponseStatus("User not found", HttpStatus.NOT_FOUND);
}
