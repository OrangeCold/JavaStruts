package com.Queue.title;

import com.Queue.entity.ArrayQueue;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class QueueTitle {

    /**
     * 用数组实现队列
     */
    @Test
    public void createQueueByArray(){
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        queue.add(1);
        queue.add(2);
        int length = queue.length;
        System.out.println("length = " + length);

        Integer integer = queue.get();
        System.out.println("integer = " + integer);

        Integer integer1 = queue.get();
        System.out.println("integer1 = " + integer1);

        Integer integer2 = queue.get();
        System.out.println("integer2 = " + integer2);

    }

}
