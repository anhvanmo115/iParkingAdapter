package com.viettel.iParkingAdapter.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;

public class TCPClient {

    Logger logger = LogManager.getLogger(TCPClient.class);
    TCPServerHandler tcpServerHandler;
    public TCPClient(int port) {
        logger.info("start connect");
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap clientBootstrap = new Bootstrap();

            clientBootstrap.group(group);
            clientBootstrap.channel(NioSocketChannel.class);
//            clientBootstrap.remoteAddress(new InetSocketAddress("203.190.173.70", port));
            clientBootstrap.remoteAddress(new InetSocketAddress("203.190.173.70", port));
            tcpServerHandler = new TCPServerHandler();
            clientBootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline()
                            .addLast(new TCPByteToMessageDecoder())
                            .addLast(tcpServerHandler);
                }
            });
            ChannelFuture channelFuture = clientBootstrap.connect().sync();
            Thread.sleep(3*1000);
            tcpServerHandler.sendMessage("123456");
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            logger.error("err",e);
        }
        finally {
//            group.shutdownGracefully().sync();
        }
    }

    public void send(){
        tcpServerHandler.sendMessage("123456");
    }
}
