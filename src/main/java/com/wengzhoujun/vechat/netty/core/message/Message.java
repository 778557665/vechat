package com.wengzhoujun.vechat.netty.core.message;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 2660401219022676872L;

    private String id;
    private String content;
    /**
     * 消息类型（0：业务消息  1：心跳消息）
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
