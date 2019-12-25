package com.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class BufferArrayTest {
    public static void main(String[] args) throws Exception{
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);

        serverSocketChannel.socket().bind(inetSocketAddress);

        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        SocketChannel socketChannel = serverSocketChannel.accept();

        int messageLenght = 8;
        while (true){
            int byteRead = 0;
            while (byteRead<messageLenght){
                long l = socketChannel.read(byteBuffers);
                byteRead += l;
                System.out.println("byteRead = " + byteRead);
                Arrays.asList(byteBuffers).stream().map(buffer -> "postion=" + buffer.position() + ", limit=" + buffer.limit()).forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).forEach(buffer -> buffer.flip());

            long byteWirte = 0;
            while (byteWirte < messageLenght){
                long l = socketChannel.write(byteBuffers);
                byteWirte += l;
            }

            Arrays.asList(byteBuffers).forEach(buffer -> {
                buffer.clear();
            });

            System.out.println("byteWirte = " + byteWirte + ", byteRead =" + byteRead + ", messageLength =" + messageLenght);
        }
    }
}
