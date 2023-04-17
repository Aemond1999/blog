package com.hya.blog.utils;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private Object data;

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static  Result okResult(int code,String msg){
        return new Result(code,msg);
    }
    public static  Result okResult(int code,String msg,Object data){
        return new Result(code,msg,data);
    }
    public static  Result failResult(int code,String msg){
        return new Result(code,msg);
    }
    public static  Result failResult(int code, String msg, Object data){
        return new Result(code,msg,data);
    }

    public Result() {

    }


}
