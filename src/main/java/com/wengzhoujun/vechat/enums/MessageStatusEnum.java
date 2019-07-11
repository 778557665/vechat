package com.wengzhoujun.vechat.enums;

/**
 * Created on 2019/7/10.
 *
 * @author WengZhoujun
 */
public enum MessageStatusEnum {

    SENDING((byte) 0, "用户名或密码不存在"),
    SENT((byte) 1, "成功"),
    SEND_FAILURE((byte) 2, "请求参数错误"),
    UNREAD((byte) 3, "token过期"),
    READED((byte) 4, "签名验证失败");

    private final Byte code;
    private final String msg;

    MessageStatusEnum(Byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Byte getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
