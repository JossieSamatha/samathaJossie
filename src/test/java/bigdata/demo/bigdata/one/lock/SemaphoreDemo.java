package bigdata.demo.bigdata.one.lock;

import java.util.concurrent.Semaphore;

/*
    示例：去餐馆吃饭
    餐馆中的桌子的数量是有限的。一旦所有的桌子都被占用，那么后来的客人就需要等待
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore s = new Semaphore(6);
        for (int i = 0; i < 10; i++) {
            new Thread(new Eater(s)).start();
        }

    }

}

// 用餐的客人
class Eater implements Runnable {

    private Semaphore s;

    public Eater(Semaphore s) {
        this.s = s;
    }

    @Override
    public void run() {

        try {
            // 每过来一波客人，就会有一张桌子被占用
            // 相当于有一个信号被占用
            // 一旦信号被全部占用，那么后来的客人就需要等待
            s.acquire();
            System.out.println("来了一波客人，一张桌子被占用");
            // 模拟：用餐时间
            Thread.sleep((long) (Math.random() * 10000));
            System.out.println("一桌客人用餐完毕，买单离开，空出来一张桌子");
            // 有一张桌子被空出，相当于有一个信号被释放，那么阻塞线程就可以获取信号来执行任务
            s.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
