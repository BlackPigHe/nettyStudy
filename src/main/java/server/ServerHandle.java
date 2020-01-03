package server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("我是服务端的channelActive方法");
        System.out.println(""+ctx.read().toString());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf= (ByteBuf) msg;
        byte[] bytes=new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String str=new String(bytes,"UTF-8");
        System.out.println("接收到客户端的消息========"+str);
        System.out.println("给客户端回复消息");
        ByteBuf buffer = Unpooled.buffer();
        byte[] bytes1 = "你好我是服务端".getBytes();
        ByteBuf byteBuf1 = buffer.writeBytes(bytes1);
        ctx.writeAndFlush(byteBuf1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
