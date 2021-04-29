package com.tutor.common.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
@Slf4j
public class ThreadExecutorSupport<T> extends AbstactThreadExecutor<T>{
    private static ExecutorService THREAD_POOL= Executors.newSingleThreadExecutor();
    private static ExecutorService LISTENER_POOL=Executors.newCachedThreadPool();
    private static int LISTENER_TIMEOUT=100;
    @Override
    public void execute(String beanName, Object para) {
        THREAD_POOL=getExecutorService(THREAD_POOL);
        Future<?> future=THREAD_POOL.submit(new AsyThread(getBean(beanName),para));
        LISTENER_POOL.execute(new ListenerThread(future,THREAD_POOL));
    }
    private  ExecutorService getExecutorService(ExecutorService executorService){
        if(executorService.isShutdown()){
            executorService=Executors.newSingleThreadExecutor();
        }
        return executorService;
    }
    class ListenerThread extends Thread{
        private final Future<?> future;
        private final ExecutorService executorService;
        public ListenerThread(Future<?> future,ExecutorService executorService){
            super();
            this.future=future;
            this.executorService=executorService;
        }
        @Override
        public void run(){
            try{
                future.get(LISTENER_TIMEOUT, TimeUnit.SECONDS);
            }catch (InterruptedException e){
                log.error("run Interrupted" ,e);
            }catch (ExecutionException e){
                log.error("run executor",e);
            }catch (TimeoutException e){
                log.error("time out res={}",e.getMessage());
            }
        }

    }
}
