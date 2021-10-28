package com.daovantam.quanlydaotao.model.response;

import com.daovantam.quanlydaotao.exception.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse {
    private ResponseStatus status;
    private Object data;

    public BaseResponse(final ResponseStatus status, final Object data) {
        this.status = status;
        this.data = data;
    }

    public BaseResponse(final ResponseStatus status) {
        this.status = status;
        this.data = null;
    }
}
