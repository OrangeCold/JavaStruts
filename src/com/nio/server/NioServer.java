package com.nio.server;


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {


    public static void main(String[] args) throws Exception{
        //创建一个ServerSocketChannel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //创建一个Selector
        Selector selector = Selector.open();
        //绑定通道监听的端口
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        //将通道设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //将通道注册到Selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            if (selector.select(1000)==0){
                System.out.println("Not client");
                continue;
            }
            //获取有活动事件的通道的SelectionKey
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //如果是连接事件
                if (key.isAcceptable()){
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("socketChannel.hashCode() = " + socketChannel.hashCode());
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                //如果是有读数据事件
                if (key.isReadable()){
                    SocketChannel socketChannel = (SocketChannel)key.channel();
                    ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
                    socketChannel.read(byteBuffer);
                    String s = new String(byteBuffer.array());
                    System.out.println("from client = " + s);
                }
                iterator.remove();
            }
        }
    }

}
