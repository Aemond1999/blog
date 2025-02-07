package com.hya.enums;

public enum AppExceptionEnum {


    INSERT_FAIL(500,"添加失败"),
    DELETE_FAIL(500,"删除失败"),
    UPDATE_FAIL(500,"修改失败"),
    ACCOUNT_OR_PWD_ERROR(500,"账号或密码错误"),
    ACCOUNT_OR_PWD_BLANK(500,"账号或密码为空"),
    LOGIN_EXPIRED(500,"登录过期"),
    NOT_LOGIN(500,"未登录"),
    UNAUTHORIZED(500,"未授权"),
    INVALID_TOKEN(500,"无效令牌"),
    REGISTER_FAIL(500,"注册失败"),
    USER_EXISTED(500,"用户已存在"),
    MISS_NECESSARY_PARAM(500,"缺少必要参数"),
    ARTICLE_NOT_EXISTS(500,"不存在该文章");
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
