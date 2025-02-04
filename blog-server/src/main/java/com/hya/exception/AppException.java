package com.hya.exception;

import com.hya.enums.AppExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AppException extends RuntimeException{
    private final int code;
    private final String msg;
    public AppException(AppExceptionEnum appExceptionEnum){
        this.code = appExceptionEnum.getCode();
        this.msg = appExceptionEnum.getMsg();
    }

}
