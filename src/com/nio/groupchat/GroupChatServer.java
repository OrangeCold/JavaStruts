package com.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupChatServer {

    //定义属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 8080;

    public GroupChatServer() {

        try {
            //创建选择器
            selector = Selector.open();
            //创建通道ServerSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定端口
            listenChannel.socket().bind(new InetSocketAddress(PORT));
            //设置为非阻塞模式
            listenChannel.configureBlocking(false);
            //将通道注册到选择器上并关注连接事件
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void listen(){
        try {
            
            while (true){
                //选择器阻塞2秒，看看有多少事件发生
                int count = selector.select();
                if (count > 0){
                    //获取有事件活动的通道的SelectionKey
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()){
                        SelectionKey key = iterator.next();
                        //如果是连接事件
                        if (key.isAcceptable()){
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线了");
                        }
                        //如果是读数据事件
                        if (key.isReadable()){
                            //专门写一个方法处理
                            readData(key);
                        }
                        //移除当前的SelectionKey,防止重复操作
                        iterator.remove();
                    }
                }
            }
            
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 读数据
     * @param key 发生读事件的通道的SelectionKey
     */
    private void readData(SelectionKey key){

        SocketChannel channel = null;

        try {

            //获取通道
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int count = channel.read(byteBuffer);
            if (count > 0){
                String msg = new String(byteBuffer.array());
                System.out.println("来自客户端的消息 " + msg);

                //向其他客户端转发消息，专门写一个方法
                sendInfoToOtherClient(msg,channel);
            }

        }catch (IOException e){

            //若发生异常，说明读不到数据
            try {
                System.out.println(channel.getRemoteAddress() + " 离线了");
                key.cancel();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 将消息转发给其他客户端
     * @param msg 消息本身
     * @param self 转发的通道本身
     * @throws IOException
     */
    private void sendInfoToOtherClient(String msg, SocketChannel self) throws IOException{
        //遍历注册到选择器上的所有通道
        for (SelectionKey key : selector.keys()) {
            Channel channel = key.channel();
            //排除通道本身
            if (channel instanceof SocketChannel && channel != self){
                SocketChannel socketChannel = (SocketChannel) channel;
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                socketChannel.write(byteBuffer);
            }
        }
    }

    public static void main(String[] args) {
        GroupChatServer groupChatServer = new GroupChatServer();
        groupChatServer.listen();
    }
}
