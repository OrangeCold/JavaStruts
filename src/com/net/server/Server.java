package com.net.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8888);
        new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    InputStream inputStream = socket.getInputStream();
                                    byte[] bytes = new byte[1024];
                                    while (true) {
                                        int len = 0;
                                        while ((len=inputStream.read(bytes)) != -1){
                                            String s = new String(bytes, 0, len);
                                            System.out.println("s = " + s);
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

}
