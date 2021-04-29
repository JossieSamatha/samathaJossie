package com.tutor.common.thread;

import com.tutor.common.factory.NamedThreadFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AbstactThreadExecutor<T> implements DisposableBean, ApplicationContextAware {
    public static final ExecutorService executorService= Executors.newCachedThreadPool(new NamedThreadFactory("a"));
    private ApplicationContext applicationContext;
    public <T> T getBean(String beanName){
        return(T)applicationContext.getBean(beanName);
    }
    @Override
    public void destroy() throws Exception {
        executorService.shutdown();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
    public abstract void execute(String beanName,T para);
}
