package com.nio.groupchat2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author chenzihan
 * @date 2019-12-30 19:16
 */
public class GroupChatClient {

    private static final String IP = "127.0.0.1";
    private static final int PORT = 8080;
    private Selector selector;
    private SocketChannel socketChannel;
    private String userName;

    public GroupChatClient() {

        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open(new InetSocketAddress(IP, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
            userName = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(userName + " is OK...");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendInfo(String msg){
        msg = userName + "say: " + msg;
        try {
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readInfo(){
        try {
            int count = selector.select();
            if (count > 0){
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        channel.read(byteBuffer);
                        String s = new String(byteBuffer.array()).trim();
                        System.out.println(s);
                    }
                }
                iterator.remove();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatClient groupChatClient = new GroupChatClient();

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            while (true){
                groupChatClient.readInfo();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String s = scanner.nextLine();
            groupChatClient.sendInfo(s);
        }
    }


}
