package com.wengzhoujun.vechat.netty.server.handler;

import com.wengzhoujun.vechat.netty.core.message.Message;
import com.wengzhoujun.vechat.netty.server.core.ConnectionPool;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created on 2019/7/5.
 *
 * @author WengZhoujun
 */
public class ServerPoHandlerKryo extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Message message = (Message) msg;
        if (ConnectionPool.getChannel(message.getId()) == null) {
            ConnectionPool.putChannel(message.getId(), ctx);
        }
        System.err.println("server:" + message.getId());
        System.err.println("server content:" + message.getContent());
        // ping
        if (message.getType() == 1) {
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }


}
