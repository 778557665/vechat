package com.wengzhoujun.vechat.controller;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.entity.User;
import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.server.core.ConnectionPool;
import com.wengzhoujun.vechat.util.PasswordUtil;
import com.wengzhoujun.vechat.util.ResponseUtil;
import com.wengzhoujun.vechat.util.UserUtils;
import io.netty.channel.ChannelHandlerContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Api(tags = "测试接口")
@RestController
@RequestMapping("/user/test")
public class TestController {


    @ApiOperation(value = "测试")
    @PostMapping("test")
    public Result test(HttpServletRequest request) throws Exception {
        return ResponseUtil.ok();
    }

    /**
     * 注册客户端
     * @param clientId  客户端ID
     * @param content	消息内容
     * @return
     */
    @GetMapping("/push/{clientId}")
    public Object pushAllMessage(@PathVariable("clientId")String clientId, String content) {
        ChannelHandlerContext channel = ConnectionPool.getChannel(clientId);
        Message message = new Message();
        message.setContent(content);
        channel.writeAndFlush(content);
        return "success";
    }
}
