package com.wengzhoujun.vechat.netty.core.message;

import com.wengzhoujun.vechat.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created on 2019/7/5.
 *
 * @author WengZhoujun
 */
public class MessageEncoder extends MessageToByteEncoder<Message> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Message message, ByteBuf byteBuf) throws Exception {
        byte[] datas = ByteUtils.objectToByte(message);
        byteBuf.writeBytes(datas);
        channelHandlerContext.flush();
    }
}
