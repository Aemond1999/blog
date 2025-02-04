package com.hya.enums;


public enum HttpCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(1, "操作失败"),
    BIZ_ERROR(1000, "通用业务异常"),
    FILE_OUT_MAX(9000, "文件超出最大限制"),
    FILE_FORMAT_ERROR(9001, "文件格式不正确"),
    PARAM_ERROR(9050, "参数错误"),
    JSON_FORMAT_ERROR(9051, "Json解析异常"),
    SQL_ERROR(9052, "Sql解析异常"),
    NETWORK_TIMEOUT(9510, "网络超时"),
    UNKNOWN_INTERFACE(9520, "未知的接口"),
    REQ_MODE_NOT_SUPPORTED(9530, "请求方式不支持"),
    SYSTEM_ERROR(9999, "系统异常"),
    NULL_POINTER(500,"空指针异常"),
    MISS_PARAM(400,"缺少必要参数"),
    LOGIN_SUCCESS(200, "登陆成功"),
    REGISTER_SUCCESS(200, "注册成功");


    /**
     * 状态码
     */
    private final int code;

    /**
     * 状态信息
     */
    private final String msg;

    HttpCodeEnum(int code, String msg) {
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
