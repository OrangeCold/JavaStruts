package com.net.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                OutputStream outputStream = socket.getOutputStream();
                while (true){
                    Scanner scanner = new Scanner(System.in);
                    String msg = scanner.nextLine();
                    outputStream.write(msg.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
