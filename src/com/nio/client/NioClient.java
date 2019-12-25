package com.nio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 8080);
        //是否连接成功
        if (!socketChannel.connect(inetSocketAddress)){
            //是否连接完成
            while (!socketChannel.finishConnect()){
                System.out.println("wait....");
            }
        }
        String str = "Hello NIO";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        socketChannel.write(byteBuffer);
        //阻塞一下，可有可无
        System.in.read();
    }
}
