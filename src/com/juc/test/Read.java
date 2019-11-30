package com.juc.test;

import com.juc.thread.ReadThread;
import com.juc.utils.ProUtil;

import java.util.HashMap;
import java.util.Map;

public class Read {

    public int i = 1;
    public boolean isOK = false;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public boolean isOK() {
        return isOK;
    }

    public void setOK(boolean OK) {
        isOK = OK;
    }

    public void begin(){
        ReadThread readThread = new ReadThread();
        isOK = true;
        readThread.setRead(this);
        new Thread(readThread).start();
    }

    public void begin2(){
        ReadThread readThread = new ReadThread();
        isOK = true;
        readThread.setRead(this);
        new Thread(readThread).start();
    }

    public void close(){
        isOK = false;
    }


}
