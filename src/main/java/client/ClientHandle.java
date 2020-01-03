package client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.channels.Channels;

public class ClientHandle extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端开始干事情了");
        byte[] bytes = "我是客户端我要搞事情了".getBytes();
        ByteBuf buffer = Unpooled.buffer();
        buffer.writeBytes(bytes);
        ctx.writeAndFlush(buffer);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("" + cause.getMessage());
        cause.printStackTrace();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        System.out.println("读取服务端发送过来的数组");
        ByteBuf bf = byteBuf;
        byte[] bytes=new byte[byteBuf.readableBytes()];
        bf.readBytes(bytes);
        String msg = new String(bytes, "UTF-8");
        System.out.println("服务端发送过来的消息：==="+msg);
    }
}
