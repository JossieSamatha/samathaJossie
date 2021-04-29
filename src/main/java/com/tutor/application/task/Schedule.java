package com.tutor.application.task;

import com.tutor.common.thread.ThreadExecutorSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 作业调度springboot  启动类需要加注解 @EnableScheduling
 */
@Component
@EnableScheduling
@Slf4j
public class Schedule {
    @Autowired
    private ThreadExecutorSupport threadExecutorSupport;
    @Scheduled(cron = "0/5 * * * * ?")//5秒执行一次
    public void run(){
        System.out.println("scheduled");
        try {
            long s=System.currentTimeMillis();
            threadExecutorSupport.execute("asyService","");
            long e=System.currentTimeMillis();
            System.out.println(e-s);
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }
}


