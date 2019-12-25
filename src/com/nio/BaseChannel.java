package com.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class BaseChannel {

    public static void write(String str) throws IOException {
        //创建一个输出流
        FileOutputStream fileOutputStream = new FileOutputStream("1.txt");
        //通过输出流获取通道
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        //将数据放入缓存区
        byteBuffer.put(str.getBytes());
        //切换缓冲区为读模式
        byteBuffer.flip();
        //读取缓存区的数据，然后写入通道
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    public static String read(String filePath) throws Exception{
        //创建一个文件对象
        File file = new File(filePath);
        //创建一个输入流
        FileInputStream fileInputStream = new FileInputStream(file);

        //获取输入流的通道
        FileChannel fileChannel = fileInputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //将通道的数据读取到缓冲区
        fileChannel.read(byteBuffer);

        //返回缓冲区中的数据
        String s = new String(byteBuffer.array());
        fileInputStream.close();
        return s;
    }

    public static void copy(String srcPath, String copyPath) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(new File(srcPath));
        FileChannel inputStreamChannel = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(copyPath));
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

        while (true){
            byteBuffer.clear();
            int read = inputStreamChannel.read(byteBuffer);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            outputStreamChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void copyByChannel(String sourceFile, String productFile) throws Exception{
        FileInputStream fileInputStream = new FileInputStream(sourceFile);
        FileOutputStream fileOutputStream = new FileOutputStream(productFile);

        FileChannel sourceChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();

        outChannel.transferFrom(sourceChannel,0,sourceChannel.size());

        fileInputStream.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) throws Exception {
//        String str = "我的天啊";
//
//        String filePath = "1.txt";
//
//        String read = read(filePath);
//        System.out.println("read = " + read);
//        copy("1.txt","2.txt");
        copyByChannel("2.txt","3.txt");
    }
}
