package com.hya.blog.enums;

public enum HttpCodeEnum {
    SUCCESS(200, "操作成功"),

    NEED_LOGIN(401,"需要登录"),
    NO_OPERATOR_AUH(403,"无权限操作"),
    USERNAME_EXIST(403,"用户名已存在"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    FAIL(400,"操作失败"),
    UNSUPPORTED_MEDIA_TYPE(415,"格式错误");


    private int code;
    private String msg;

    HttpCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
