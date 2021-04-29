package com.tutor.common.thread;


public class AsyThread<T> extends Thread{
    /**
     *
     */
    public AsyThreadExcute asyThreadExcute;
    private T para;
    public AsyThread(AsyThreadExcute asyThreadExcute){
        super();
        this.asyThreadExcute=asyThreadExcute;
    }
    public AsyThread(AsyThreadExcute asyThreadExcute,T para){
        super();
        this.asyThreadExcute=asyThreadExcute;
        this.para=para;
    }
    public void run(){
        asyThreadExcute.execute(para);
    }
}
