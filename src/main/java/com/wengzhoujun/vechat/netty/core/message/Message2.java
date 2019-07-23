package com.wengzhoujun.vechat.netty.core.message;

import java.io.Serializable;

public class Message2 implements Serializable {

    private static final long serialVersionUID = -5492304608359586947L;

    private String fromUserId;
    private String toUserId;
    private String content;
    /**
     * 消息类型（0：业务消息  1：心跳消息）
     */
    private Byte type;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }
}
