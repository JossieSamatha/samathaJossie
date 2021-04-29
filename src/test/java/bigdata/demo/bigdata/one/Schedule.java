package bigdata.demo.bigdata.one;

import com.tutor.SatyrApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 作业调度springboot
 */
@EnableScheduling
public class Schedule {
    public static void main(String[] args) {
        SpringApplication.run(SatyrApplication.class, args);
    }
    @Scheduled(cron = "0/5 * * * * ?")//5秒执行一次
    public static void run(){
        System.out.println("scheduled");
    }
}

