package com.Queue.entity;

public class GuiguArrayQueue<T> {

    private int maxSize;
    private int front;
    private int rear;
    private T[] queue;

    public GuiguArrayQueue(int max) {
        queue = (T[])new Object[max];
        maxSize = max;
        front = -1;
        rear = -1;
    }

    /**
     * 判断是否为空
     */
    public boolean isEntity(){
        return front == rear;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull(){
        return rear == maxSize-1;
    }

    /**
     * 队列此时的数据量，长度
     */
    public int length(){
        return rear-front;
    }

    /**
     * 添加数据
     */
    public void add(T t){
        if (isFull()){
            throw new RuntimeException("队列已满");
        }
        rear++;
        queue[rear]=t;
    }

    /**
     * 取出数据
     */
    public T get(){
        if (isEntity()){
            throw new RuntimeException("队列为空");
        }
        front++;
        return queue[front];
    }

    /**
     * 展示所有数据
     */
    public void show(){
        if (isEntity()){
            throw new RuntimeException("队列为空");
        }
        for (T t : queue) {
            System.out.print(t.toString()+",");
        }
    }

    /**
     * 显示队列的头部，不取出数据
     */
    public T head(){
        if (isEntity()){
            throw new RuntimeException("队列为空");
        }
        return queue[front+1];
    }

}
