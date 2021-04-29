package bigdata.demo.bigdata.one.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo2 {

    public static void main(String[] args) throws InterruptedException {

        // BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();
        // 队列为空
        // 抛出NoSuchElementException
        // System.out.println(queue.remove());
        // 返回null
        // System.out.println(queue.poll());
        // 永久阻塞
        // System.out.println(queue.take());
        // 定时阻塞
        System.out.println(queue.poll(5, TimeUnit.SECONDS));

    }

}
