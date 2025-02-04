package com.hya.enums;

public enum AppExceptionEnum {


    DELETE_FAIL(500,"删除失败"),
    UPDATE_FAIL(500,"修改失败");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态信息
     */
    private final String msg;

   AppExceptionEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
