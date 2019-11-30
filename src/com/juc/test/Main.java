package com.juc.test;

import com.juc.thread.ReadThread;
import com.juc.utils.ProUtil;

public class Main {
    public static void main(String[] args) {

        Read read = new Read();
        read.begin();
        Read read2 = new Read();
        read2.begin2();

        while (true){
            String s = ReadThread.map.get(ProUtil.OPEN);
            if ("222222".equals(s)) {
                System.out.println("s2 = " + s);
                ReadThread.map.remove(ProUtil.OPEN);
                break;
            }

        }

        String s = ReadThread.map.get(ProUtil.OPEN);
        System.out.println("open = " + s);

        new Thread(){
            @Override
            public void run() {
                while (true){
                    String s1 = ReadThread.map.get(ProUtil.READ);
                    if ("333333".equals(s1)) {
                        System.out.println("s1 = " + s1);
                        ReadThread.map.remove(ProUtil.READ);
                        read.close();
                        read2.close();
                        break;
                    }

                }
            }
        }.start();

    }


}
