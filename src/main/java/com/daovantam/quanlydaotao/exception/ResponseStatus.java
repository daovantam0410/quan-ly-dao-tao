package com.daovantam.quanlydaotao.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public final class ResponseStatus {
    private String message;
    private HttpStatus httpCode;
}
