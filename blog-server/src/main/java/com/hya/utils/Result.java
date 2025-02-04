package com.hya.utils;

import com.hya.enums.HttpCodeEnum;
import com.hya.exception.AppException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
public class Result {
    private Object data;
    private Boolean success;
    private int code;
    private String msg;

    public static Result success(Object data) {
        return new Result(data, true, HttpCodeEnum.SUCCESS.getCode(), HttpCodeEnum.SUCCESS.getMsg());
    }

    public static Result fail(int code, String msg) {
        return new Result(null, false, code, msg);
    }

    public static Result fail(AppException appException) {
        return new Result(null, false,
                appException.getCode(), appException.getMsg());
    }

    public static Result fail(HttpCodeEnum httpCodeEnum) {
        return new Result(null, false, httpCodeEnum.getCode(), httpCodeEnum.getMsg());
    }


}

