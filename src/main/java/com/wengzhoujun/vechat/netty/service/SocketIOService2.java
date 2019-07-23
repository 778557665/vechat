package com.wengzhoujun.vechat.netty.service;

import com.wengzhoujun.vechat.netty.core.message.Message2;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
public interface SocketIOService2 {

    //推送的事件
    String PUSH_EVENT = "push_event";

    String CLIENT_TO_CLIENT = "client_to_client";

    String CLIENT_TO_CLIENT2 = "client_to_client2";

    // 启动服务
    void start() throws Exception;

    // 停止服务
    void stop();

    // 推送信息
    void pushMessageToUser(Message2 message);
}
