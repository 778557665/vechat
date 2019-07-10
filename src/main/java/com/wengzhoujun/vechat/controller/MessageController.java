package com.wengzhoujun.vechat.controller;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.service.SocketIOService;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 2019/7/10.
 *
 * @author WengZhoujun
 */
@Api(tags = "消息接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private SocketIOService socketIOService;

    @ApiOperation(value = "发送消息至用户")
    @PostMapping("/pushMessage")
    public Result pushMessage(@ApiParam(value = "接收消息的用户id") @RequestParam Long userId,
                              @ApiParam(value = "消息内容") @RequestParam String content) {
        String userIdStr = userId.toString();
        Message message = new Message();
        message.setId(userIdStr);
        message.setContent(content);
        socketIOService.pushMessageToUser(message);
        return ResponseUtil.ok();
    }

}
