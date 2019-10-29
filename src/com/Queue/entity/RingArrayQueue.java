package com.Queue.entity;

public class RingArrayQueue<T> {

    int front;
    int rear;
    int maxSize;
    T[] queue;

    public RingArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        queue = (T[])new Object[maxSize];
    }

    /**
     * 判断环形队列是否已满
     */
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty(){
        return front == rear;
    }

    /**
     * 队列有效数据的个数
     */
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 添加数据
     */
    public void add(T t){
        if (isFull()){
            throw new RuntimeException("队列已满");
        }
        queue[rear] = t;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 取出数据
     */
    public T get(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        T v = queue[front];
        front = (front + 1)%maxSize;
        return v;
    }

    /**
     * 展示所有数据
     */
    public void show(){
        if (isEmpty()){
            throw new RuntimeException("队列为空");
        }
        for (int i = front; i < front + size(); i++) {
            System.out.print(queue[i%maxSize] + ",");
        }
        System.out.println();
    }

    /**
     * 返回队列第一个数据的值
     */
    public T head(){
        if (isEmpty()) {
            throw new RuntimeException("队列为空");
        }
        return queue[front];
    }

}
