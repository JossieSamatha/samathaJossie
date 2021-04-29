package bigdata.demo.bigdata.one.lock;

import java.util.concurrent.Exchanger;

/*
    示例：生产消费
    生产者将商品交换给消费者
    消费者应该将钱交换给生产者
 */
public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<String> ex = new Exchanger<>();
        new Thread(new Producer(ex)).start();
        new Thread(new Consumer(ex)).start();

    }

}

// 生产者
class Producer implements Runnable {

    private Exchanger<String> ex;

    public Producer(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        try {
            // 生产者准备商品
            String info = "商品";
            // 生产者将商品交换给消费者，同时应该受到消费者换过来的钱
            String msg = ex.exchange(info);
            System.out.println("生产者收到消费者换过来的：" + msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

// 消费者
class Consumer implements Runnable {

    private Exchanger<String> ex;

    public Consumer(Exchanger<String> ex) {
        this.ex = ex;
    }

    @Override
    public void run() {
        try {
            // 消费者准备钱
            String info = "钱";
            // 消费者将钱交换给生产者，同时应该受到生产者换过来的商品
            String msg = ex.exchange(info);
            System.out.println("消费者收到生产者换过来的：" + msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
