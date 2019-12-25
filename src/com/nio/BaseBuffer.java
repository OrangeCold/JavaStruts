package com.nio;

import java.nio.IntBuffer;

public class BaseBuffer {
    public static void main(String[] args) {
        //create an intBuffer
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //put data
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }

        //turn model
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println("i = " + i);
        }


        intBuffer.clear();
        while (intBuffer.hasRemaining()){
            int i = intBuffer.get();
            System.out.println("i = " + i);
        }
    }
}
