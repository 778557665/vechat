package com.wengzhoujun.vechat.controller;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.service.SocketIOService;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created on 2019/7/10.
 *
 * @author WengZhoujun
 */
@Api(tags = "消息接口")
@RestController
@RequestMapping("/message")
public class MessageController {
//
//    @Autowired
//    private SocketIOService socketIOService;
//
//    @ApiOperation(value = "发送消息至用户")
//    @PostMapping("/push")
//    public Result pushMessage(@ApiParam(value = "接收消息的用户id") @RequestParam String userId,
//                              @ApiParam(value = "消息内容") @RequestParam String content) {
//        Message message = new Message();
//        message.setId(userId);
//        message.setContent(content);
//        socketIOService.pushMessageToUser(message);
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 推送消息给所有客户端
//     * @param content	消息内容
//     * @return
//     */
//    @ApiOperation(value = "发送消息至多个用户")
//    @PostMapping("/pushAll")
//    public Result pushAllMessage(@ApiParam(value = "接收消息的用户ids") @RequestBody List<String> ids,
//                                 @ApiParam(value = "消息内容") @RequestParam String content) {
//        ids.forEach(c -> {
//            Message message = new Message();
//            message.setId(c);
//            message.setContent(content);
//            socketIOService.pushMessageToUser(message);
//        });
//        return ResponseUtil.ok();
//    }

}
