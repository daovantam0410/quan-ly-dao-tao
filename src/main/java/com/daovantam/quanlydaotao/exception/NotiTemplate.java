package com.daovantam.quanlydaotao.exception;

import org.springframework.http.HttpStatus;

public interface NotiTemplate {
    ResponseStatus SUCCESS = new ResponseStatus("SUCCESS", HttpStatus.OK);
    ResponseStatus USER_NOT_FOUND = new ResponseStatus("User not found", HttpStatus.NOT_FOUND);
    ResponseStatus EXPIRE_TOKEN =
            new ResponseStatus("token expired", HttpStatus.BAD_REQUEST);
    ResponseStatus TOKEN_IN_VALID =
            new ResponseStatus( "invalid token", HttpStatus.BAD_REQUEST);
}
