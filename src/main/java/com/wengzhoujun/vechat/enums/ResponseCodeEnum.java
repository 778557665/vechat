package com.wengzhoujun.vechat.enums;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
public enum ResponseCodeEnum {

    USERORPASSWORD_ERRROR("userorpassword_errror", "用户名或密码不存在"),
    SUCCESS("success", "成功"),
    SYS_PARAM_NOT_RIGHT("sys_param_not_right", "请求参数错误"),
    TOKEN_EXPIRE("token_expire", "token过期"),
    SIGNATURE_ERROR("signature_error", "签名验证失败"),
    QUERY_DATA_NOT_EXIST("query_data_not_exist", "请求数据不存在"),
    SYSTEM_BUSY("system_busy", "系统繁忙，请稍候重试");

    private final String code;
    private final String msg;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
