package com.wengzhoujun.vechat.netty.server.controller;

import com.wengzhoujun.vechat.domain.Result;
import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.server.core.ConnectionPool;
import com.wengzhoujun.vechat.util.ResponseUtil;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息API,提供消息的基本操作
 * @author yinjihuan
 *
 */
@RestController
@RequestMapping("/message")
public class MessageController {
	
	/**
	 * 推送消息给所有客户端
	 * @param content	消息内容
	 * @return
	 */
	@GetMapping("/push")
	public Result pushAllMessage(String content) {
		ConnectionPool.getChannels().forEach(c -> {
			Message message = new Message();
			message.setContent(content);
			c.writeAndFlush(message);
		});
		return ResponseUtil.ok();
	}
	
	/**
	 * 推送消息给指定客户端
	 * @param clientId  客户端ID
	 * @param content	消息内容
	 * @return
	 */
	@GetMapping("/push/{clientId}")
	public Result pushAllMessage(@PathVariable("clientId")String clientId, String content) {
		ChannelHandlerContext channel = ConnectionPool.getChannel(clientId);
		Message message = new Message();
		message.setContent(content);
		channel.writeAndFlush(message);
		return ResponseUtil.ok();
	}

}
