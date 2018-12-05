package com.viettel.iParkingAdapter.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TCPServer {

    private int port;

    EventLoopGroup bossGroup;
    EventLoopGroup workerGroup;
    ChannelFuture f;
    ServerBootstrap bootstrap;

    Logger logger = LogManager.getLogger(TCPServer.class);

    public TCPServer(int port) {
        this.port = port;
        //config server option
        init();
    }

    private void init(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try{
            bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline()
                                    .addLast(new TCPByteToMessageDecoder())
                                    .addLast(new TCPServerHandler());
                        }
                    });

        }catch (Exception e){
            logger.error("init server error",e);
        }
    }

    public void startServer(){
        logger.info("start server ... Listen port " + port);
        try{
            f = bootstrap.bind(port).sync();
            logger.info("start done");
        }catch(Exception e){
            logger.error("start server error",e);
        }
    }

    public void stopServer(){
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }
}