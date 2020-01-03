package server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ServerChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerTest {
    public static void main(String[] args){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        serverBootstrap.group(eventExecutors)
                .channel(NioServerSocketChannel.class)
                .localAddress(9999)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChanne) throws Exception {
                        socketChanne.pipeline().addFirst(new ServerHandle());
                    }
                });
        try {
            ChannelFuture sync = serverBootstrap.bind().sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
