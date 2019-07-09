package com.wengzhoujun.vechat.netty.core.message;

import com.wengzhoujun.vechat.netty.core.serialize.kryo.KryoSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class KryoDecoder extends ByteToMessageDecoder {
	
	@Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		Object obj = KryoSerializer.deserialize(in);
        out.add(obj);
    }
	
}
