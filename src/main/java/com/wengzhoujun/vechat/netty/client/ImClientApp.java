package com.wengzhoujun.vechat.netty.client;

import com.wengzhoujun.vechat.netty.core.message.Message;
import io.netty.channel.Channel;

import java.util.UUID;

/**
 * IM 客户端启动入口
 * @author yinjihuan
 */
public class ImClientApp {
	public static final String HOST = "127.0.0.1";
	public static int PORT = 2222;
	public static void main(String[] args) {
		Channel channel = new ImConnection().connect(HOST, PORT);
		String id = UUID.randomUUID().toString().replaceAll("-", "");

		Message message = new Message();
		message.setId(id);
		message.setContent("hello yinjihuan");
		channel.writeAndFlush(message);
		//对象传输数据
		/*Message message = new Message();
		message.setId(id);
		message.setContent("hello yinjihuan");
		channel.writeAndFlush(message);*/
		//字符串传输数据
		//channel.writeAndFlush("yinjihuan");
	}
}
