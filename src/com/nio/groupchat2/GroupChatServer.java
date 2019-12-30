package com.nio.groupchat2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author chenzihan
 * @date 2019-12-27 16:39
 */
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8080;

    public GroupChatServer(){
        try {

            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            listenChannel.configureBlocking(false);
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void listen(){
        while (true){
            try {
                int count = selector.select();
                if (count > 0){
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()){
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线了");
                        }
                        if (key.isReadable()){
                            // 写一个读取数据的方法
                            readData(key);
                        }
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readData(SelectionKey key){
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = socketChannel.read(byteBuffer);
            if (read > 0){
                String msg = new String(byteBuffer.array());
                System.out.println("来自客户端的消息：" + msg);
                // 写一个转发消息方法
                sendMsg(socketChannel, msg);
            }

        } catch (IOException e) {
            try {
                System.out.println(socketChannel.getRemoteAddress() + " 离线了");
                key.cancel();
                socketChannel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void sendMsg(SocketChannel self, String msg){
        Iterator<SelectionKey> iterator = selector.keys().iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self){
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    socketChannel.write(byteBuffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }

}
