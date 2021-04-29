package bigdata.demo.bigdata.one.lock;

import java.util.concurrent.CyclicBarrier;

/*
    示例：跑步比赛
    运动员先到起跑线，听到枪响之后再分别往外跑
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cb = new CyclicBarrier(6);
        new Thread(new Runner(cb), "1号").start();
        new Thread(new Runner(cb), "2号").start();
        new Thread(new Runner(cb), "3号").start();
        new Thread(new Runner(cb), "4号").start();
        new Thread(new Runner(cb), "5号").start();
        new Thread(new Runner(cb), "6号").start();

    }

}

class Runner implements Runnable {

    private CyclicBarrier cb;

    public Runner(CyclicBarrier cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            // 模拟：运动员走到起跑线的时间
            Thread.sleep((long) (Math.random() * 10000));
            // 获取名字
            String name = Thread.currentThread().getName();
            System.out.println(name + "运动员走到了起跑线");
            // 正常逻辑：运动员到了起跑线之后需要等待，等其他的运动员都到了之后才能往外跑
            // 在所有线程都到达这个点之前，先到的线程应该阻塞
            // await方法不只是阻塞，还会减少一个计数
            // 当计数归零的时候，会自动的放开阻塞
            cb.await();
            System.out.println(name + "运动员跑了出去");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
