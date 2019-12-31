package com.nio.groupchat3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * @author chenzihan
 * @date 2019-12-31 14:17
 */
public class GroupChatServer {
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 8080;

    public GroupChatServer() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen(){
        while (true){
            try {
                int count = selector.select();
                if (count > 0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()){
                            SocketChannel channel = serverSocketChannel.accept();
                            channel.configureBlocking(false);
                            channel.register(selector, SelectionKey.OP_READ);
                            System.out.println(channel.getRemoteAddress() + " 上线了。。。");
                        }
                        if (key.isReadable()){
                            // 写一个读数据的方法
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

    private void readData(SelectionKey key){
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            int read = channel.read(byteBuffer);
            if (read > 0){
                String s = new String(byteBuffer.array());
                System.out.println("来自客户端的消息：" + s);
                // 写一个转发消息的方法
                sendData(channel, s);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                key.cancel();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    private void sendData(SocketChannel self, String msg){

        Iterator<SelectionKey> iterator = selector.keys().iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            Channel channel = key.channel();
            if (channel instanceof SocketChannel && channel != self){
                SocketChannel socketChannel = (SocketChannel) channel;
                try {
                    socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
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
