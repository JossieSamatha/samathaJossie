package bigdata.demo.bigdata.one.lock;

import java.util.concurrent.CountDownLatch;

/*
    示例：考试
    考官和考生先后到达考场
    开始考试
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        // 底层依然是依靠同步锁来实现的
        CountDownLatch cdl = new CountDownLatch(7);
        new Thread(new Teacher(cdl)).start();
        new Thread(new Teacher(cdl)).start();
        new Thread(new Student(cdl)).start();
        new Thread(new Student(cdl)).start();
        new Thread(new Student(cdl)).start();
        new Thread(new Student(cdl)).start();
        new Thread(new Student(cdl)).start();

        // 应该是考官和考生都到了考场之后，考试才能开始
        // 而在上面的线程执行完成之前，这个线程应该阻塞
        // 在计数归零之前，需要让当前线程陷入阻塞
        // 当计数归零之后，自动的放开阻塞
        cdl.await();
        System.out.println("考试开始~~~");

    }

}

// 考官
class Teacher implements Runnable {

    private CountDownLatch cdl;

    public Teacher(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            // 模拟：考官到达考场的时间
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("考官到达考场~~~");
            // 减少一个计数
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

// 考生
class Student implements Runnable {

    private CountDownLatch cdl;

    public Student(CountDownLatch cdl) {
        this.cdl = cdl;
    }

    @Override
    public void run() {
        try {
            // 模拟：考生到达考场的时间
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("考生到达考场~~~");
            cdl.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
