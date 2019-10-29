package com.Queue.entity;

/**
 * 数组模拟队列
 */
public class ArrayQueue<T> {

    /**
     * 最大容量
     */
    public int maxSize = 10;

    /**
     * 队列长度
     */
    public int length = 0;

    /**
     * 对头指针
     */
    private int front = 0;

    /**
     * 队尾指针
     */
    private int reat = 0;

    private T[] arrayQueue;

    public ArrayQueue() {
        arrayQueue = (T[])new Object[maxSize];
    }

    /**
     * 添加数据
     * @param t 添加的元素
     */
    public int add(T t){
        if (length <= maxSize){
            arrayQueue[reat] = t;
            reat++;
            maxSize--;
            length++;
            return 1;
        }else{
            return 0;
        }
    }

    /**
     * 取出数据
     */
    public T get(){
        if (length!=0){
            if (front<reat){

                T t = arrayQueue[front];
                maxSize++;
                length--;
                front++;

                return t;
            }
        }
        return null;
    }

}
