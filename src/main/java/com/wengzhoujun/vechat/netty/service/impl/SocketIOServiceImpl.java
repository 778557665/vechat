package com.wengzhoujun.vechat.netty.service.impl;

import com.corundumstudio.socketio.AckCallback;
import com.corundumstudio.socketio.BroadcastAckCallback;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.core.message.Message2;
import com.wengzhoujun.vechat.netty.service.SocketIOService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Slf4j
//@Service
public class SocketIOServiceImpl implements SocketIOService {

    // 用来存已连接的客户端
    private static Map<String, SocketIOClient> clientMap = new ConcurrentHashMap<>();

    @Autowired
    private SocketIOServer socketIOServer;

    /**
     * Spring IoC容器创建之后，在加载SocketIOServiceImpl Bean之后启动
     *
     * @throws Exception
     */
    @PostConstruct
    private void autoStartup() throws Exception {
        start();
    }

    /**
     * Spring IoC容器在销毁SocketIOServiceImpl Bean之前关闭,避免重启项目服务端口占用问题
     *
     * @throws Exception
     */
    @PreDestroy
    private void autoStop() throws Exception {
        stop();
    }

    @Override
    public void start() {
        // 监听客户端连接
        socketIOServer.addConnectListener(client -> {
            log.info(client.getRemoteAddress() + "web客户端接入");
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.put(loginUserNum, client);
            }
        });

        // 监听客户端断开连接
        socketIOServer.addDisconnectListener(client -> {
            String loginUserNum = getParamsByClient(client);
            if (loginUserNum != null) {
                clientMap.remove(loginUserNum);
                client.disconnect();
            }
        });

        // 处理自定义的事件，与连接监听类似 通知全部
        socketIOServer.addEventListener(PUSH_EVENT, Message.class, (client, data, ackSender) -> {
            // TODO do something
            socketIOServer.getBroadcastOperations().sendEvent(PUSH_EVENT, data);
        });

        //1对1
        socketIOServer.addEventListener(CLIENT_TO_CLIENT, Message.class, (client, data, ackSender) -> {
            // TODO do something
            System.out.println(data);
            if (null != clientMap && null != data) {
                clientMap.get(data.getId()).sendEvent(CLIENT_TO_CLIENT, data);
            }
        });

        socketIOServer.start();
    }

    @Override
    public void stop() {
        if (socketIOServer != null) {
            socketIOServer.stop();
            socketIOServer = null;
        }
    }

    @Override
    public void pushMessageToUser(Message message) {
        String id = message.getId();
        if (StringUtils.isNotBlank(id)) {
            SocketIOClient client = clientMap.get(id);
            if (client != null) {
                client.sendEvent(PUSH_EVENT, message);
            }
        }
    }

    /**
     * 此方法为获取client连接中的参数，可根据需求更改
     *
     * @param client
     * @return
     */
    private String getParamsByClient(SocketIOClient client) {
        // 从请求的连接中拿出参数（这里的loginUserNum必须是唯一标识）
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        List<String> list = params.get("id");
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    public static void main(String[] args) {
        Message data = new Message();
        data.setId("1");
        data.setContent("asdasda");
        System.out.println(Arrays.asList(data));
    }
}
