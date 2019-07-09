package com.wengzhoujun.vechat.netty.core.message;

import com.wengzhoujun.vechat.util.ByteUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created on 2019/7/5.
 *
 * @author WengZhoujun
 */
public class MessageDecoder extends ByteToMessageDecoder{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Object o = ByteUtils.byteToObject(ByteUtils.read(byteBuf));
        list.add(o);
    }
}
