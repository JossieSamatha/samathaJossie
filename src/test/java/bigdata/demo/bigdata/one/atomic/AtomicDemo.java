package bigdata.demo.bigdata.one.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {

    // static int i = 0;
    static AtomicInteger ai = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);
        new Thread(new Sum(cdl)).start();
        new Thread(new Sum(cdl)).start();

        // 应该是上述两个线程执行完成之后才能打印结果
        cdl.await();
        System.out.println(ai);
    }

}

class Sum implements Runnable {

    private CountDownLatch cdl;

    public Sum(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            // AtomicDemo.i++;
            AtomicDemo.ai.incrementAndGet();
        }
        cdl.countDown();
    }
}