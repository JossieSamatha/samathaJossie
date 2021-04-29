package bigdata.demo.bigdata.one.atomic;

import java.util.concurrent.CountDownLatch;

public class VolatileDemo2 {

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch cdl = new CountDownLatch(2);
        Data d = new Data();
        new Thread(new Add(d, cdl)).start();
        new Thread(new Add(d, cdl)).start();

        cdl.await();
        System.out.println(d.i);

    }

}

class Add implements Runnable {

    private Data d;
    private CountDownLatch cdl;

    public Add(Data d, CountDownLatch cdl) {
        this.d = d;
        this.cdl = cdl;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            d.i++;
        }
        cdl.countDown();
    }

}