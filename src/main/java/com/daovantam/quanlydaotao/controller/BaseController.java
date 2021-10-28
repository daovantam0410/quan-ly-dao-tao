package com.daovantam.quanlydaotao.controller;

import com.daovantam.quanlydaotao.exception.NotiTemplate;
import com.daovantam.quanlydaotao.model.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BaseController {

    public ResponseEntity<BaseResponse> success(){
        BaseResponse response = new BaseResponse(NotiTemplate.SUCCESS);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<BaseResponse> success(Object object){
        BaseResponse response = new BaseResponse(NotiTemplate.SUCCESS, object);
        return ResponseEntity.ok(response);
    }
}
