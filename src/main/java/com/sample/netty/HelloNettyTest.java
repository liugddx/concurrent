package com.sample.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <p>@ClassName HelloNettyTest</p>
 * <p>@description TODO</p>
 *
 * @author Ethan.liu
 * @version 1.0
 * @date 2021/8/12 14:12
 */
public class HelloNettyTest {

    public static void main(String[] args) {
        //boss线程组
        EventLoopGroup bossGroup
                = new NioEventLoopGroup(1);
        //worker线程组
        EventLoopGroup workerGroup
                = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup).channel(NioServerSocketChannel.class).childHandler(
                    new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) {
                                ch.pipeline().addLast(new EchoServerHandler());
                        }
                    });

            ChannelFuture f = serverBootstrap.bind(9090).sync();
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }




    }

    static class EchoServerHandler extends ChannelInboundHandlerAdapter {

        // 处理读事件
        @Override
        public void channelRead(
                ChannelHandlerContext ctx, Object msg){
            ctx.write(msg);
        }
        //处理读完成事件
        @Override
        public void channelReadComplete(
                ChannelHandlerContext ctx){
            ctx.flush();
        }
        //处理异常事件
        @Override
        public void exceptionCaught(
                ChannelHandlerContext ctx,
                Throwable cause) {
            cause.printStackTrace();
            ctx.close();
        }
    }
}
