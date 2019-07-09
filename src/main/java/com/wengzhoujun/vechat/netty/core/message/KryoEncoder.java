package com.wengzhoujun.vechat.netty.core.message;

import com.wengzhoujun.vechat.netty.core.serialize.kryo.KryoSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class KryoEncoder extends MessageToByteEncoder<Message> {
	
	@Override
	protected void encode(ChannelHandlerContext ctx, Message message, ByteBuf out) throws Exception {
		KryoSerializer.serialize(message, out);
		ctx.flush();
	}
	
}
