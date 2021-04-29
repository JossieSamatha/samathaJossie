package bigdata.demo.bigdata.one.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        // 参数表示容量
        // 容量指定之后不可变
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(5);

        // 添加元素
        // 在队列没有满的情况下，这三种方法几乎没有差别
        // queue.add("a");
        // queue.offer("b");
        // queue.put("c");

        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("a");
        queue.add("a");

        // 队列已满
        // 抛出IllegalStateException
        // queue.add("b");
        // 返回false
        // boolean b = queue.offer("c");
        // System.out.println(b);
        // 永久阻塞
        // queue.put("d");
        // 定时阻塞
        boolean b = queue.offer("f", 5, TimeUnit.SECONDS);
        System.out.println(b);

        System.out.println(queue);

    }

}
