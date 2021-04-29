package bigdata.demo.bigdata.one.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时调度执行器服务 - 对任务(线程)来进行定时调度，在Java中，很多的定时机制的底层都会依靠这个线程池
 */
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {

        // 构建线程池对象
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(5);
        // 执行任务
        // 推迟任务执行的时间 - 表示5s之后再执行ScheduleThread
         ses.schedule(new ScheduleThread(), 5, TimeUnit.SECONDS);

        // 定时调度任务
        // 每隔5s执行一次任务 thread.sleep(3000); 每隔5秒执行一次
        // 从上一个任务的启动开始，来计算下一个任务的启动时间
        // 在任务执行时间和间隔时间之间取最大值，哪个大，就以哪个时间为实际的间隔时间
        ses.scheduleAtFixedRate(new ScheduleThread(), 0, 5, TimeUnit.SECONDS);

        // 每个5s执行一次任务 thread.sleep(3000); 每隔8秒执行一次
        // 从上一个任务的结束开始，来计算下一个任务的启动时间
         ses.scheduleWithFixedDelay(new ScheduleThread(), 0, 5, TimeUnit.SECONDS);

    }

}

class ScheduleThread implements Runnable {
    @Override
    public void run() {
        try {
            System.out.println("running");
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
