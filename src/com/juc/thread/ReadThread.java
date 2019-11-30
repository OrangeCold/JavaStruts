package com.juc.thread;

import com.juc.test.Read;
import com.juc.utils.ProUtil;

import java.util.HashMap;
import java.util.Map;

public class ReadThread implements Runnable{

    public volatile static Map<String, String> map = new HashMap<String, String>();

    private Read read;

    public void setRead(Read read) {
        this.read = read;
    }

    @Override
    public void run() {
        while (read.isOK()){
            read.setI(read.getI()+1);
            if (String.valueOf(read.getI()).equals("222222")){
                map.put(ProUtil.OPEN,String.valueOf(read.getI()));
            }
            if (String.valueOf(read.getI()).equals("333333")){
                map.put(ProUtil.READ,String.valueOf(read.getI()));
            }
        }

    }

}
