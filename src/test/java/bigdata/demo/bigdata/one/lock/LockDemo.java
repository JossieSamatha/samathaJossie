package bigdata.demo.bigdata.one.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
// import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    static int i = 0;

    public static void main(String[] args) throws InterruptedException {

        // Lock lock = new ReentrantLock();
        // 获取读写锁
        ReadWriteLock rw = new ReentrantReadWriteLock(true);//ture公平锁 false非公平锁 默认false
        // 获取写锁
//        Lock lock=rw.readLock();
        Lock lock = rw.writeLock();
        new Thread(new Add(lock)).start();
        new Thread(new Add(lock)).start();

        // 主函数所在的类也是一个线程，那么主线程就会和其他线程进行抢占
        Thread.sleep(2000);
        System.out.println(i);

    }

}

class Add implements Runnable {

    private Lock lock;

    public Add(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        // 加锁
        lock.lock();
        for (int i = 0; i < 100000; i++) {
            LockDemo.i++;
        }
        // 解锁
        lock.unlock();
    }

}


