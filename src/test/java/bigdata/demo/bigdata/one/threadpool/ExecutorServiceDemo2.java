package bigdata.demo.bigdata.one.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 预定义线程池
 */
public class ExecutorServiceDemo2 {

    public static void main(String[] args) {

        // 预定义线程池
        /* 特点
            1. 没有核心线程全部都是临时线程
            2. 临时线程线程数量是Integer.MAX_VALUE。考虑到单台服务器的线程承载量远远达不到21亿
               所以一般认为这个线程池能够处理无限多的请求
            3. 临时线程的存活时间是1min
            4. 工作队列是一个同步队列
         */
        // 大池子小队列
        // 适应于高并发的短任务场景，例如即时通讯 - QQ、微信
         ExecutorService esC = Executors.newCachedThreadPool();
        /*public static ExecutorService newCachedThreadPool() {
            return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }*/


        /* 特点
            1. 全部都是核心线程没有临时线程
            2. 临时线程的存活时间是0s
            3. 工作队列是一个阻塞式链式队列且没有指定容量，那么工作队列的默认容量就是Integer.MAX_VALUE
               一般认为这个队列能够存储无限多的请求
         */
        // 小池子大队列
        // 适应于并发低、长任务场景，例如文件下载 - 百度云盘
         ExecutorService esf = Executors.newFixedThreadPool(5);
        /*public static ExecutorService newFixedThreadPool(int nThreads) {
            return new ThreadPoolExecutor(nThreads, nThreads,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
        }*/

    }

}
