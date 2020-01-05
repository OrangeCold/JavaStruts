package com.netty.http;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class TestServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //向管道添加处理器
        //得到管道
        ChannelPipeline pipeline = socketChannel.pipeline();

        //加入一个 netty 提供的 httpServerCodec codec => [coder - decoder]
        //HttpServerCodec 说明
        //HttpServerCodec 是 netty 提供的处理 http 的 编-解码器
        pipeline.addLast("MyHttpServerCode", new HttpServerCodec());
        //增加一个自定义的 handler
        pipeline.addLast("MyTestHttpServerHandler", new TestHttpServerHandler());
    }
}
